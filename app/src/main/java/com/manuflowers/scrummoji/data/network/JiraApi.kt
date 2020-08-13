package com.manuflowers.scrummoji.data.network

import com.manuflowers.scrummoji.data.model.GetSprintsResponse
import com.manuflowers.scrummoji.data.model.SprintStoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JiraApi {
    @GET("board/1/sprint")
    suspend fun getSprints(): GetSprintsResponse

    @GET("sprint/{sprintId}/issue")
    suspend fun getSprintStories(@Path("sprintId") sprintId: Int): SprintStoriesResponse
}

const val BASE_URL = "https://manumobileteam.atlassian.net/rest/agile/1.0/"
const val HISTORIES_URL = ""