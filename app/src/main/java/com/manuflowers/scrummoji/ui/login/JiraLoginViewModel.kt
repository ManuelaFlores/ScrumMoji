package com.manuflowers.scrummoji.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuflowers.scrummoji.data.network.BasicAuthentication
import com.manuflowers.scrummoji.repository.JiraRepositoryImpl
import kotlinx.coroutines.launch

class JiraLoginViewModel(
    private val repository: JiraRepositoryImpl,
    private val basicAuthentication: BasicAuthentication
) : ViewModel() {

    fun loginUser(userName: String, password: String) {
        viewModelScope.launch {
            basicAuthentication.createCredentials(userName, password)
            val response = repository.getSprints(basicAuthentication).values
            Log.e("ERRORRRRR", response.toString())
        }
    }
}