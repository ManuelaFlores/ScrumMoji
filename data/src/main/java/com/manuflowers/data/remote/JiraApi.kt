package com.manuflowers.data.remote

import com.manuflowers.data.repository.sprints.model.SprintsData
import com.manuflowers.data.repository.stories.model.StoriesData
import retrofit2.http.*

interface JiraApi {
    @GET("board/1/sprint")
    suspend fun getSprints(@Header("Authorization") basicAuth: String): SprintsData

    @GET("sprint/{sprintId}/issue")
    suspend fun getSprintStories(
        @Header("Authorization") basicAuth: String,
        @Path("sprintId") sprintId: Int
    ): StoriesData

    /*
    @PUT("issue/{storyId}/estimation")
    suspend fun updateSprintStory(
        @Path("storyId") storyId: String,
        @Query("boardId") boardId: Int,
        @Body updateSprintStoryRequest: UpdateSprintStoryRequest
    ): UpdateSprintStoryResponse*/
}