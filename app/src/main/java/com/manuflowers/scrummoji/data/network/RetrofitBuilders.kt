package com.manuflowers.scrummoji.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun buildApiService(userName: String, password: String): JiraApi = buildRetrofit(
    userName = userName,
    password = password
)
    .create(JiraApi::class.java)

fun buildRetrofit(userName: String, password: String): Retrofit {
    return Retrofit.Builder()
        .client(
            buildClient(
                userName = userName,
                password = password
            )
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .build()
}

fun buildClient(userName: String, password: String): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(
            BasicAuthentication()
        )
        .build()
}

//fmanuela499@gmail.com
//yld9o0tWcUioCNgDVq9663E0