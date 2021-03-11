package com.manuflowers.data.di

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.manuflowers.data.remote.JiraApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val networkModule = module {

    single { buildClient(get()) }

    single { buildRetrofit(get(), BASE_URL) }

    single { Firebase.database.reference }
}

const val BASE_URL = "https://manumobileteam.atlassian.net/rest/agile/1.0/"

fun buildApiJiraService(interceptor: Interceptor): JiraApi =
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