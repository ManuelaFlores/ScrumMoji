package com.manuflowers.scrummoji.data.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthentication : Interceptor {
    //private val credentials = Credentials.basic(username = userName, password = password)

    private var userName = ""
    private var password = ""

    fun createCredentials(userName: String, password: String) {
        this.userName = userName
        this.password = password

        //return Credentials.basic(username = userName, password = password)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", Credentials.basic(username = userName, password = password))
            .build()
        return chain.proceed(request)
    }
}