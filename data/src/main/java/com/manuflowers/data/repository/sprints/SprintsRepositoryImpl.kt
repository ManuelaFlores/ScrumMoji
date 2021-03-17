package com.manuflowers.data.repository.sprints

import com.manuflowers.data.repository.sprints.model.toData
import com.manuflowers.data.repository.sprints.model.toEntity
import com.manuflowers.data.repository.sprints.source.SprintsDataSource
import com.manuflowers.domain.sprints.SprintsRepository
import com.manuflowers.domain.sprints.models.SprintsEntity
import com.manuflowers.domain.sprints.models.UserCredentialEntity
import com.manuflowers.domain.utils.Result

class SprintsRepositoryImpl(
    private val sprintsDataSource: SprintsDataSource
) : SprintsRepository {
    override suspend fun getSprints(userCredentialEntity: UserCredentialEntity): Result<SprintsEntity> {
        return when (val result = sprintsDataSource.getSprints(userCredentialEntity.toData())) {
            is Result.Success -> Result.Success(result.data.toEntity())
            is Result.Error -> result
            is Result.NetworkError -> result
        }
    }
}