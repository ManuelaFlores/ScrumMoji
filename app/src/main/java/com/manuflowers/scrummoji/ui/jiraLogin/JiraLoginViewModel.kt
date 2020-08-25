package com.manuflowers.scrummoji.ui.jiraLogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuflowers.scrummoji.data.model.Failure
import com.manuflowers.scrummoji.data.model.Success
import com.manuflowers.scrummoji.data.model.UserCredential
import com.manuflowers.scrummoji.data.network.BasicAuthentication
import com.manuflowers.scrummoji.repository.JiraRepositoryImpl
import com.manuflowers.scrummoji.repository.UserRepositoryImpl
import com.manuflowers.scrummoji.ui.jiraLogin.viewstate.JiraLogin
import com.manuflowers.scrummoji.ui.jiraLogin.viewstate.JiraLoginError
import com.manuflowers.scrummoji.ui.jiraLogin.viewstate.JiraLoginSuccess
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class JiraLoginViewModel(
    private val repository: JiraRepositoryImpl,
    private val userRepositoryImpl: UserRepositoryImpl,
    private val basicAuthentication: BasicAuthentication
) : ViewModel() {

    private val jiraDataMutableLiveData = MutableLiveData<JiraLogin>()
    val jiraLiveData: LiveData<JiraLogin>
        get() = jiraDataMutableLiveData

    fun loginUser(userName: String, password: String) {
        viewModelScope.launch {
            val userCredentials = UserCredential(userName, password)
            basicAuthentication.createCredentials(userCredentials)
            when (val response = repository.getSprints(basicAuthentication)) {
                is Success -> {
                    response.data.collect {
                        userRepositoryImpl.setUserCredentials(userCredentials)
                        userRepositoryImpl.setUserLoggedIn(true)
                        jiraDataMutableLiveData.postValue(JiraLoginSuccess(it))
                    }
                }
                is Failure -> {
                    jiraDataMutableLiveData.postValue(
                        JiraLoginError(response.error.localizedMessage ?: "")
                    )
                }
            }
        }
    }
}