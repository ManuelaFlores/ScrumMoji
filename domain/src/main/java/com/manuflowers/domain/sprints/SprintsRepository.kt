package com.manuflowers.domain.sprints

import com.manuflowers.domain.sprints.models.SprintsEntity
import com.manuflowers.domain.sprints.models.UserCredentialEntity
import com.manuflowers.domain.utils.Result

interface SprintsRepository {
    suspend fun getSprints(userCredentialEntity: UserCredentialEntity): Result<SprintsEntity>
}