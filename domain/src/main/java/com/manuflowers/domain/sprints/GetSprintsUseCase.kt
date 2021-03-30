package com.manuflowers.domain.sprints

import com.manuflowers.domain.sprints.model.SprintsEntity
import com.manuflowers.domain.sprints.model.UserCredentialEntity
import com.manuflowers.domain.utils.Result

class GetSprintsUseCase(
    private val sprintsRepository: SprintsRepository
) {
    suspend fun getSprints(userCredentialEntity: UserCredentialEntity): Result<SprintsEntity> {
        return sprintsRepository.getSprints(userCredentialEntity)
    }
}