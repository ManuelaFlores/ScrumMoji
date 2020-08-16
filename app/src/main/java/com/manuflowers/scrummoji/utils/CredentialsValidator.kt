package com.manuflowers.scrummoji.utils

interface CredentialsValidator {
    fun setCredentials(username: String, password: String)

    fun isUsernameValid(): Boolean

    fun isPasswordValid(): Boolean

    fun areCredentialsValid(): Boolean
}