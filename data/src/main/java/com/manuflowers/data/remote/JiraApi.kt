package com.manuflowers.data.remote

import com.manuflowers.data.repository.sprints.model.SprintsEntity
import retrofit2.Response
import retrofit2.http.*

interface JiraApi {
    @GET("board/1/sprint")
    suspend fun getSprints(): SprintsEntity

    /*@GET("sprint/{sprintId}/issue")
    suspend fun getSprintStories(@Path("sprintId") sprintId: Int): SprintStoriesResponse

    @PUT("issue/{storyId}/estimation")
    suspend fun updateSprintStory(
        @Path("storyId") storyId: String,
        @Query("boardId") boardId: Int,
        @Body updateSprintStoryRequest: UpdateSprintStoryRequest
    ): UpdateSprintStoryResponse*/
}