package com.manuflowers.scrummoji.repository

import com.manuflowers.scrummoji.data.model.*
import com.manuflowers.scrummoji.data.network.ApiFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import okhttp3.Interceptor
import retrofit2.HttpException
import java.io.IOException

class JiraRepositoryImpl(
    private val apiFactory: ApiFactory
) {

    suspend fun getSprints(interceptor: Interceptor): Result<Flow<GetSprintsResponse>> {
        return try {
            val result = apiFactory.buildApiJiraService(interceptor).getSprints()
            Success(flowOf(result))
        } catch (e: IOException) {
            Failure(e)
        } catch (e: HttpException) {
            Failure(e)
        }
    }

    suspend fun getSprintStories(
        sprintId: Int,
        interceptor: Interceptor
    ): Result<Flow<SprintStoriesResponse>> {
        return try {
            val result =
                apiFactory.buildApiJiraService(interceptor).getSprintStories(sprintId = sprintId)
            Success(flowOf(result))
        } catch (e: IOException) {
            Failure(e)
        } catch (e: HttpException) {
            Failure(e)
        }
    }

}