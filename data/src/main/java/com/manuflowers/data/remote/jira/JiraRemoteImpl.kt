package com.manuflowers.data.remote.jira

import com.manuflowers.data.remote.JiraApi
import com.manuflowers.data.repository.sprints.model.SprintsEntity
import com.manuflowers.data.utils.safeCall
import com.manuflowers.domain.utils.Result
import kotlinx.coroutines.Dispatchers

class JiraRemoteImpl(
    private val jiraApi: JiraApi
): JiraRemote {
    override suspend fun getSprints():
            Result<SprintsEntity> {
        return safeCall(Dispatchers.IO) {
            jiraApi.getSprints()
        }
    }
}