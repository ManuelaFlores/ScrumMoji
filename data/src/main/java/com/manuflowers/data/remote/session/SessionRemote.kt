package com.manuflowers.data.remote.session

interface SessionRemote {
    fun registerNewSession(sessionPath: String, onSuccessListener: (sessionPath: String) -> Unit, date: String)
}