package com.manuflowers.data.remote.jira

import com.manuflowers.data.repository.sprints.model.SprintsEntity
import com.manuflowers.domain.utils.Result
import retrofit2.Response

interface JiraRemote {
    suspend fun getSprints(): Result<SprintsEntity>
}