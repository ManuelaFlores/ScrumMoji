package com.manuflowers.data.repository.stories.source.remote

import android.util.Base64
import com.manuflowers.data.remote.JiraApi
import com.manuflowers.data.repository.sprints.model.UserCredentialData
import com.manuflowers.data.repository.stories.model.StoriesData
import com.manuflowers.data.repository.stories.model.StoryData
import com.manuflowers.data.repository.stories.model.StoryValueData
import com.manuflowers.data.repository.stories.source.StoriesDataSource
import com.manuflowers.data.utils.safeCall
import com.manuflowers.domain.utils.Result
import kotlinx.coroutines.Dispatchers

class StoriesRemoteDataSource(
    private val jiraApi: JiraApi
) : StoriesDataSource {
    override suspend fun getStories(
        sprintId: Int,
        userCredentialData: UserCredentialData
    ): Result<StoriesData> {
        val basicAuthentication = "${userCredentialData.userName}:${userCredentialData.password}"
        val authHeader =
            "Basic ${Base64.encodeToString(basicAuthentication.toByteArray(), Base64.NO_WRAP)}"
        return safeCall(Dispatchers.IO) {
            jiraApi.getSprintStories(
                basicAuth = authHeader,
                sprintId = sprintId
            )
        }
    }

    override suspend fun updateStory(
        userCredentialData: UserCredentialData,
        storyId: String,
        boardId: Int,
        storyValueData: StoryValueData
    ): Result<StoryData> {
        val basicAuthentication = "${userCredentialData.userName}:${userCredentialData.password}"
        val authHeader =
            "Basic ${Base64.encodeToString(basicAuthentication.toByteArray(), Base64.NO_WRAP)}"
        return safeCall(Dispatchers.IO) {
            jiraApi.updateSprintStory(
                basicAuth = authHeader,
                storyId = storyId,
                boardId = boardId,
                storyValueData = storyValueData
            )
        }
    }
}