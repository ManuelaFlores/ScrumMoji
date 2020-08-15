package com.manuflowers.scrummoji.ui.jiraLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.ui.jiraLogin.viewstate.JiraLoginError
import com.manuflowers.scrummoji.ui.jiraLogin.viewstate.JiraLoginSuccess
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedActivity
import com.manuflowers.scrummoji.utils.toast
import kotlinx.android.synthetic.main.activity_jira_login.*
import org.koin.android.ext.android.inject

class JiraLoginActivity : AppCompatActivity() {

    private val viewModel: JiraLoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jira_login)
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.jiraLiveData.observe(this, Observer { jiraLogin ->
            when (jiraLogin) {
                is JiraLoginSuccess -> {
                    val intent = Intent(this, SprintsFeedActivity::class.java)
                    intent.putExtra(LOGIN_DATA, jiraLogin.data)
                    startActivity(intent)
                }
                is JiraLoginError -> {
                    this.toast(jiraLogin.error)
                }
            }
        })
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

    companion object {
        val LOGIN_DATA = "LOGIN_DATA"
    }
}