package com.manuflowers.domain.session.usecase

import com.manuflowers.domain.session.repository.SessionRepository

class GenerateSessionUseCase(
    private val sessionRepository: SessionRepository
) {
    fun registerNewSession(
        onSuccessListener: (sessionPath: String) -> Unit,
        date: String,
        uuid: String
    ) {
        sessionRepository.registerNewSession(
            onSuccessListener, date, uuid
        )
    }
}