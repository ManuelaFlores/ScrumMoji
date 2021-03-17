package com.manuflowers.data.di

import com.manuflowers.data.remote.JiraApi
import com.manuflowers.data.repository.sprints.source.SprintsDataSource
import com.manuflowers.data.repository.sprints.source.remote.SprintsRemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single { buildClient() }

    single { buildRetrofit(get(), BASE_URL) }

    single { buildApiJiraService() }
}

const val BASE_URL = "https://manumobileteam.atlassian.net/rest/agile/1.0/"

fun buildApiJiraService(): JiraApi =
    buildRetrofit(buildClient(), BASE_URL).create(JiraApi::class.java)

private fun buildRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
}

private fun buildClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}