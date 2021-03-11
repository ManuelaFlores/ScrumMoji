package com.manuflowers.domain.session.repository

interface SessionRepository {
    fun registerNewSession(
        onSuccessListener: (sessionPath: String) -> Unit,
        date: String,
        uuid: String
    )
}