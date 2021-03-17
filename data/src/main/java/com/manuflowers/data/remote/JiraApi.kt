package com.manuflowers.data.remote

import com.manuflowers.data.repository.sprints.model.SprintsData
import retrofit2.http.*

interface JiraApi {
    @GET("board/1/sprint")
    suspend fun getSprints(@Header("Authorization") basicAuth: String): SprintsData

    /*@GET("sprint/{sprintId}/issue")
    suspend fun getSprintStories(@Path("sprintId") sprintId: Int): SprintStoriesResponse

    @PUT("issue/{storyId}/estimation")
    suspend fun updateSprintStory(
        @Path("storyId") storyId: String,
        @Query("boardId") boardId: Int,
        @Body updateSprintStoryRequest: UpdateSprintStoryRequest
    ): UpdateSprintStoryResponse*/
}