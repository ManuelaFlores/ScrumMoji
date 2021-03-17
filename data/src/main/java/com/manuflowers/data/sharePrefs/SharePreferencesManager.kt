package com.manuflowers.data.sharePrefs

import android.content.SharedPreferences
import com.manuflowers.data.repository.sprints.model.UserCredentialData

class SharePreferencesManager(
    private val preferences: SharedPreferences
) {
    fun setUserCredentials(userName: String, password: String) {
        preferences.edit().putString(KEY_USER_EMAIL, userName).apply()
        preferences.edit().putString(KEY_USER_PASSWORD, password).apply()
    }

    fun getUserCredentials() = UserCredentialData(
        userName = preferences.getString(KEY_USER_EMAIL, "") ?: "",
        password = preferences.getString(KEY_USER_PASSWORD, "") ?: ""
    )

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        preferences.edit().putBoolean(KEY_LOGGED_IN, isLoggedIn).apply()
    }

    fun isUserLoggedIn() = preferences.getBoolean(KEY_LOGGED_IN, false)

    fun saveSessionPath(sessionPath: String) {
        preferences.edit().putString(KEY_SESSION_PATH, sessionPath).apply()
    }

    fun getSessionPath() = preferences.getString(KEY_SESSION_PATH, "")

    fun saveRoomId(roomId: String) {
        preferences.edit().putString(KEY_ROOM_ID, roomId).apply()
    }

    fun getRoomId() = preferences.getString(KEY_ROOM_ID, "")

    fun saveDeveloperNickname(nickname: String) {
        preferences.edit().putString(KEY_DEVELOPER_NICKNAME, nickname).apply()
    }

    fun getDeveloperNickname() = preferences.getString(KEY_DEVELOPER_NICKNAME, "")

    companion object {
        private const val KEY_LOGGED_IN = "KEY_LOGGED_IN"
        private const val KEY_USER_PASSWORD = "KEY_USER_PASSWORD"
        private const val KEY_USER_EMAIL = "KEY_USER_EMAIL"
        private const val KEY_SESSION_PATH = "KEY_SESSION_PATH"
        private const val KEY_ROOM_ID = "KEY_ROOM_ID"
        private const val KEY_DEVELOPER_NICKNAME = "KEY_DEVELOPER_NICKNAME"
    }
}