package com.manuflowers.scrummoji.repository

import com.manuflowers.scrummoji.data.local.preferences.SharePreferencesManager
import com.manuflowers.scrummoji.data.model.UserCredential

class UserRepositoryImpl(
    private val sharedPreferencesManager: SharePreferencesManager
) {

    fun setUserCredentials(userCredential: UserCredential) =
        sharedPreferencesManager.setUserCredentials(userCredential)

    fun getUserCredentials() = sharedPreferencesManager.getUserCredentials()

    fun setUserLoggedIn(isLoggedIn: Boolean) = sharedPreferencesManager.setUserLoggedIn(isLoggedIn)

    fun isUserLoggedIn() = sharedPreferencesManager.isUserLoggedIn()

    fun saveRoomId(roomId: String) = sharedPreferencesManager.saveRoomId(roomId)

    fun getRoomId() = sharedPreferencesManager.getRoomId()

    fun saveDeveloperNickname(nickname: String) =
        sharedPreferencesManager.saveDeveloperNickname(nickname)

    fun getDeveloperNickname() = sharedPreferencesManager.getDeveloperNickname()

}