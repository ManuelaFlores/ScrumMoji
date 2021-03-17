package com.manuflowers.data.repository.sprints.source.remote

import android.util.Base64
import com.manuflowers.data.remote.JiraApi
import com.manuflowers.data.repository.sprints.model.SprintsData
import com.manuflowers.data.repository.sprints.model.UserCredentialData
import com.manuflowers.data.repository.sprints.source.SprintsDataSource
import com.manuflowers.data.utils.safeCall
import com.manuflowers.domain.utils.Result
import kotlinx.coroutines.Dispatchers

class SprintsRemoteDataSource(
    private val jiraApi: JiraApi
) : SprintsDataSource{
    override suspend fun getSprints(userCredentialData: UserCredentialData): Result<SprintsData> {
        val basicAuthentication = "${userCredentialData.userName}:${userCredentialData.password}"
        val authHeader = "Basic ${Base64.encodeToString(basicAuthentication.toByteArray(), Base64.NO_WRAP)}"
        return safeCall(Dispatchers.IO) { jiraApi.getSprints(authHeader) }
    }
}