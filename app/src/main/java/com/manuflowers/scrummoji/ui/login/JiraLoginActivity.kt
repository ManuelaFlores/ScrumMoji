package com.manuflowers.scrummoji.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.network.buildApiService
import com.manuflowers.scrummoji.repository.JiraRepositoryImpl
import kotlinx.android.synthetic.main.activity_jira_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class JiraLoginActivity : AppCompatActivity() {

    private val viewModel: JiraLoginViewModel by inject()

    //private val jiraRepositoryImpl = JiraRepositoryImpl(buildApiService())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jira_login)
        //prueba()
        setupListeners()
    }

    private fun prueba() {
        GlobalScope.launch {
            //val response = jiraRepositoryImpl.getSprints()
            //Log.e("RESPONSE", "${response.values}")
        }
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
            viewModel.loginUser(userName = getUserData(), password = getPasswordData())
        }
    }

    private fun getUserData(): String {
       return userEditText.text?.trim().toString()
    }

    private fun getPasswordData(): String {
        return passwordEditText.text?.trim().toString()
    }
}