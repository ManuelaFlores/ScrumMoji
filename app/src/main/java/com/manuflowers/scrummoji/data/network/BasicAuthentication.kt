package com.manuflowers.scrummoji.data.network

import com.manuflowers.scrummoji.data.model.UserCredential
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthentication : Interceptor {

    private var userName = ""
    private var password = ""

    fun createCredentials(userCredential: UserCredential) {
        this.userName = userCredential.userName
        this.password = userCredential.password
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", Credentials.basic(username = userName, password = password))
            .build()
        return chain.proceed(request)
    }

    //fmanuela499@gmail.com
    //yld9o0tWcUioCNgDVq9663E0
}