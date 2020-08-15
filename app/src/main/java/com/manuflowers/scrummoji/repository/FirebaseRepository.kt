package com.manuflowers.scrummoji.repository

import android.util.Log
import com.google.firebase.database.*
import com.manuflowers.scrummoji.data.local.preferences.SharePreferencesManager
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

    companion object {
        const val PATH_SESSIONS = "SESSIONS"
        const val PATH_STORIES = "STORIES"
        const val PATH_STORY_POINTS = "STORY_POINTS"
    }
}

@IgnoreExtraProperties
data class UserStory(
    var title: String = "",
    var id: String = ""
)