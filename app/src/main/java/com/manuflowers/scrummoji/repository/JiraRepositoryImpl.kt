package com.manuflowers.scrummoji.repository

import com.manuflowers.scrummoji.data.model.GetSprintsResponse
import com.manuflowers.scrummoji.data.network.ApiFactory
import okhttp3.Interceptor

class JiraRepositoryImpl(
    private val apiFactory: ApiFactory
) {

    suspend fun getSprints(interceptor: Interceptor): GetSprintsResponse =
        apiFactory.buildApiJiraService2(interceptor).getSprints()

}