package com.manuflowers.scrummoji.data.network

import com.manuflowers.scrummoji.data.model.GetSprintsResponse
import com.manuflowers.scrummoji.data.model.SprintStoriesResponse
import com.manuflowers.scrummoji.data.model.UpdateSprintStoryRequest
import com.manuflowers.scrummoji.data.model.UpdateSprintStoryResponse
import retrofit2.http.*

interface JiraApi {
    @GET("board/1/sprint")
    suspend fun getSprints(): GetSprintsResponse

    @GET("sprint/{sprintId}/issue")
    suspend fun getSprintStories(@Path("sprintId") sprintId: Int): SprintStoriesResponse

    @PUT("issue/{storyId}/estimation")
    suspend fun updateSprintStory(
        @Path("storyId") storyId: String,
        @Query("boardId") boardId: Int,
        @Body updateSprintStoryRequest: UpdateSprintStoryRequest
    ): UpdateSprintStoryResponse
}

const val BASE_URL = "https://manumobileteam.atlassian.net/rest/agile/1.0/"
const val HISTORIES_URL = ""

const val NEW_URL = "https://manumobileteam.atlassian.net/rest/api/2/<resource-name>"