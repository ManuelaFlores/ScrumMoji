package com.manuflowers.scrummoji

import retrofit2.http.GET

interface JiraApi {
    @GET("board/1/sprint")
    suspend fun getSprints(): GetSprintsResponse
}

const val BASE_URL = "https://manumobileteam.atlassian.net/rest/agile/1.0/"