package com.manuflowers.scrummoji.ui.roomLogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuflowers.scrummoji.repository.UserRepositoryImpl
import com.manuflowers.scrummoji.ui.roomLogin.viewstate.*
import com.manuflowers.scrummoji.utils.CredentialsValidator

class RoomLoginViewModel(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val roomCredentialsValidator: CredentialsValidator
) : ViewModel() {

    private val loginViewStateMutableLiveData = MutableLiveData<RoomLoginViewState>()
    val loginViewStateLiveData: LiveData<RoomLoginViewState>
        get() = loginViewStateMutableLiveData

    fun checkUserCredentials(nickName: String, roomId: String) {
        roomCredentialsValidator.setCredentials(nickName, roomId)
        checkRoomId()
        checkUserNickname()
        if (roomCredentialsValidator.areCredentialsValid()) {
            userRepositoryImpl.saveRoomId(roomId)
            userRepositoryImpl.saveDeveloperNickname(nickName)
            loginViewStateMutableLiveData.postValue(UserLoggedIn)
        }
    }

    private fun checkRoomId() {
        if (!roomCredentialsValidator.isPasswordValid()) {
            loginViewStateMutableLiveData.postValue(InvalidPassword)
        } else {
            loginViewStateMutableLiveData.postValue(ValidPassword)
        }
    }

    private fun checkUserNickname() {
        if (!roomCredentialsValidator.isUsernameValid()) {
            loginViewStateMutableLiveData.postValue(InvalidUserNickname)
        } else {
            loginViewStateMutableLiveData.postValue(ValidUserNickname)
        }
    }

}