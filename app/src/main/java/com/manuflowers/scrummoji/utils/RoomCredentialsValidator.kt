package com.manuflowers.scrummoji.utils

class RoomCredentialsValidator : CredentialsValidator {

    private lateinit var username: String
    private lateinit var password: String

    override fun setCredentials(username: String, password: String) {
        this.username = username
        this.password = password
    }

    override fun isUsernameValid(): Boolean {
        return username.trim().length >= 4
    }

    override fun isPasswordValid(): Boolean {
        return password.length >= 15
    }

    override fun areCredentialsValid(): Boolean {
        return isUsernameValid() && isPasswordValid()
    }
}