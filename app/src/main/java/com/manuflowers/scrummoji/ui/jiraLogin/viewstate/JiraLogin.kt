package com.manuflowers.scrummoji.ui.jiraLogin.viewstate

import com.manuflowers.scrummoji.data.model.GetSprintsResponse

sealed class JiraLogin

class JiraLoginSuccess(val data: GetSprintsResponse) : JiraLogin()
class JiraLoginError(val error: String) : JiraLogin()