package com.manuflowers.scrummoji.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiFactory {

    fun buildApiJiraService(userName: String, password: String): JiraApi = buildRetrofit(
        userName = userName,
        password = password
    ).create(JiraApi::class.java)

    fun buildApiJiraService2(interceptor: Interceptor): JiraApi =
        buildRetrofit(buildClient(interceptor), BASE_URL).create(JiraApi::class.java)

    private fun buildRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
    }

    private fun buildClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(interceptor)
            .build()
    }
}