package com.manuflowers.scrummoji.repository

import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.manuflowers.scrummoji.data.local.preferences.SharePreferencesManager
import com.manuflowers.scrummoji.data.model.*
import java.util.*

class FirebaseRepository(
    private val database: DatabaseReference,
    private val sharedPreferencesManager: SharePreferencesManager
) {

    private val String.getNameOfEmail: String
        get() = this.substringBefore('@')

    private fun generateSessionPath() {
        sharedPreferencesManager.saveSessionPath(
            "${sharedPreferencesManager.getUserCredentials().userName.getNameOfEmail}${UUID.randomUUID()}"
        )
    }

    fun registerNewSession(onSuccessListener: (sessionPath: String) -> Unit) {
        generateSessionPath()
        database
            .child(PATH_SESSIONS)
            .child(sharedPreferencesManager.getSessionPath() ?: "")
            .setValue("${Calendar.getInstance().time}")
            .addOnSuccessListener {
                onSuccessListener.invoke(sharedPreferencesManager.getSessionPath() ?: "")
            }.addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun sendStoryToDatabase(
        userStory: UserStory,
        onSuccessListener: (sessionPath: String) -> Unit
    ) {
        database
            .child(PATH_STORIES)
            .child(sharedPreferencesManager.getSessionPath() ?: "")
            .child(userStory.id)
            .setValue(userStory)
            .addOnSuccessListener {
                onSuccessListener.invoke(userStory.title)
            }.addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun sendStoryEstimationToDatabase(
        storyPointEstimation: StoryPointEstimation,
        onSuccessListener: () -> Unit
    ) {
        val reference = database
            .child(PATH_STORY_POINTS)
            .child(sharedPreferencesManager.getRoomId() ?: "")
            .child(storyPointEstimation.storyId).push()

        reference.setValue(storyPointEstimation).addOnSuccessListener {
            onSuccessListener.invoke()
        }.addOnFailureListener {
            it.printStackTrace()
        }
    }

    fun listenNewEstimationUploaded(
        storyId: String,
        onChildAddedListener: (result: Result<StoryPointEstimation>) -> Unit
    ) {
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val result = dataSnapshot.getValue<StoryPointEstimation>() ?: return
                onChildAddedListener.invoke(Success(result))
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(databaseError: DatabaseError) {
                onChildAddedListener.invoke(Failure(databaseError.toException()))
            }
        }

        database
            .child(PATH_STORY_POINTS)
            .child(sharedPreferencesManager.getRoomId() ?: "")
            .child(storyId)
            .addChildEventListener(childEventListener)
    }

    fun getAllEstimationsUploaded(
        storyId: String,
        onChildrenListener: (result: Result<List<StoryPointEstimation>>) -> Unit
    ) {

        val singleValueEvent = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                onChildrenListener.invoke(Failure(error.toException()))
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val result = snapshot.children.map { it.getValue<StoryPointEstimation>() ?: return }
                if (result.isNotEmpty()) {
                    onChildrenListener.invoke(Success(result))
                }
            }
        }

        database
            .child(PATH_STORY_POINTS)
            .child(sharedPreferencesManager.getRoomId() ?: "")
            .child(storyId)
            .addListenerForSingleValueEvent(singleValueEvent)
    }

    fun listenNewStoriesUploaded(onChildAddedListener: (result: Result<UserStory>) -> Unit) {
        val childEventListener = object : ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
                onChildAddedListener.invoke(Failure(error.toException()))
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val result = snapshot.getValue<UserStory>() ?: return
                onChildAddedListener.invoke(Success(result))
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {}
        }

        database
            .child(PATH_STORIES)
            .child(sharedPreferencesManager.getRoomId() ?: "")
            .addChildEventListener(childEventListener)
    }

    companion object {
        const val PATH_SESSIONS = "SESSIONS"
        const val PATH_STORIES = "STORIES"
        const val PATH_STORY_POINTS = "STORY_POINTS"
    }
}