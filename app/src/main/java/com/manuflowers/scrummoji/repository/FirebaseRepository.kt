package com.manuflowers.scrummoji.repository

import android.os.Parcelable
import android.util.Log
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.manuflowers.scrummoji.data.local.preferences.SharePreferencesManager
import com.manuflowers.scrummoji.data.model.Failure
import com.manuflowers.scrummoji.data.model.Result
import com.manuflowers.scrummoji.data.model.Success
import kotlinx.android.parcel.Parcelize
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

    //TODO: Add a sealed class as a parameter of this callback, so I can map a failure case
    fun registerNewSession(onSuccessListener: (sessionPath: String) -> Unit) {
        //FIXME: handle this exception
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
        database
            .child(PATH_STORY_POINTS)
            .child(sharedPreferencesManager.getRoomId() ?: "")
            .child(storyPointEstimation.storyId)
            .child(sharedPreferencesManager.getDeveloperNickname()?:"")
            .setValue(storyPointEstimation)
            .addOnSuccessListener {
                onSuccessListener.invoke()
            }.addOnFailureListener {
                it.printStackTrace()
            }
    }

    fun addRealTimeListener() {
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d("TAG", "onChildAdded:" + dataSnapshot.key)
                val comment = dataSnapshot.value
                Log.d("TAG", "onChildAddeddddd:" + dataSnapshot.value)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("TAG", "postComments:onCancelled", databaseError.toException())
            }
        }

        database
            .child(PATH_STORIES)
            .child(sharedPreferencesManager.getSessionPath() ?: "")
            .addChildEventListener(childEventListener)
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
                Log.d("TAG", "onChildAddeddddd:$result")
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

@Parcelize
@IgnoreExtraProperties
data class UserStory(
    @PropertyName("title") var title: String = "",
    @PropertyName("id") var id: String = ""
) : Parcelable

@IgnoreExtraProperties
data class StoryPointEstimation(
    @PropertyName("userNickname") val userNickname: String = "",
    @PropertyName("storyId") val storyId: String = "",
    @PropertyName("storyPoints") var storyPoints: Int = 0
)