package com.manuflowers.scrummoji

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthentication(userName: String, password: String) : Interceptor {
    private val credentials = Credentials.basic(username = userName, password = password)

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}