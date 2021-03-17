package com.manuflowers.data.repository.session

import com.manuflowers.data.remote.session.SessionRemote
import com.manuflowers.data.sharePrefs.SharePreferencesManager
import com.manuflowers.domain.session.repository.SessionRepository

class SessionRepositoryImpl(
    private val sessionRemote: SessionRemote,
    private val sharePreferencesManager: SharePreferencesManager
) : SessionRepository {

    private val String.getNameOfEmail: String
        get() = this.substringBefore('@')

    private fun generateSessionPath(uuid: String) {
        sharePreferencesManager.saveSessionPath(
            "${sharePreferencesManager.getUserCredentials().userName.getNameOfEmail}${uuid}"
        )
    }

    override fun registerNewSession(
        onSuccessListener: (sessionPath: String) -> Unit,
        date: String,
        uuid: String
    ) {
        generateSessionPath(uuid)
        val sessionPath = sharePreferencesManager.getSessionPath() ?: ""
        sessionRemote.registerNewSession(
            sessionPath = sessionPath,
            date = date, onSuccessListener = { onSuccessListener.invoke(sessionPath) }
        )
    }
}