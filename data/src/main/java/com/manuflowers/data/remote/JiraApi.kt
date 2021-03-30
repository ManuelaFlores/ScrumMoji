package com.manuflowers.data.remote

import com.manuflowers.data.repository.sprints.model.SprintsData
import com.manuflowers.data.repository.stories.model.StoriesData
import com.manuflowers.data.repository.stories.model.StoryData
import com.manuflowers.data.repository.stories.model.StoryValueData
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Body

interface JiraApi {
    @GET("board/1/sprint")
    suspend fun getSprints(@Header("Authorization") basicAuth: String): SprintsData

    @GET("sprint/{sprintId}/issue")
    suspend fun getSprintStories(
        @Header("Authorization") basicAuth: String,
        @Path("sprintId") sprintId: Int
    ): StoriesData

    @PUT("issue/{storyId}/estimation")
    suspend fun updateSprintStory(
        @Header("Authorization") basicAuth: String,
        @Path("storyId") storyId: String,
        @Query("boardId") boardId: Int,
        @Body storyValueData: StoryValueData
    ): StoryData
}