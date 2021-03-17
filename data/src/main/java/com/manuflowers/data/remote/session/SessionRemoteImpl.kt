package com.manuflowers.data.remote.session

import com.google.firebase.database.DatabaseReference

class SessionRemoteImpl(
    private val databaseReference: DatabaseReference
) : SessionRemote {

    //"${Calendar.getInstance().time}"
    override fun registerNewSession(
        sessionPath: String,
        onSuccessListener: (sessionPath: String) -> Unit,
        date: String
    ) {
        databaseReference
            .child(PATH_SESSIONS)
            .child(sessionPath)
            .setValue(date)
            .addOnSuccessListener {
                onSuccessListener.invoke(sessionPath)
            }.addOnFailureListener {
                it.printStackTrace()
            }
    }

    companion object {
        const val PATH_SESSIONS = "SESSIONS"
        const val PATH_STORIES = "STORIES"
        const val PATH_STORY_POINTS = "STORY_POINTS"
    }
}