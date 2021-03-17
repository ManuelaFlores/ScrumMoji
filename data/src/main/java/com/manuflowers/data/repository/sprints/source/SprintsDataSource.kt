package com.manuflowers.data.repository.sprints.source

import com.manuflowers.data.repository.sprints.model.SprintsEntity
import com.manuflowers.data.repository.sprints.model.UserCredentialEntity
import com.manuflowers.domain.utils.Result

interface SprintsDataSource {
    suspend fun getSprints(userCredentialEntity: UserCredentialEntity): Result<SprintsEntity>
}