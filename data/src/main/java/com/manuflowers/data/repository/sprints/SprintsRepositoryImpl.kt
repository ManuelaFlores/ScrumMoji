package com.manuflowers.data.repository.sprints

import com.manuflowers.domain.sprints.SprintsRepository
import com.manuflowers.domain.sprints.models.SprintsEntity
import com.manuflowers.domain.sprints.models.UserCredentialEntity
import com.manuflowers.domain.utils.Result

class SprintsRepositoryImpl(

) : SprintsRepository{
    override suspend fun getSprints(userCredentialEntity: UserCredentialEntity): Result<SprintsEntity> {
        TODO("Not yet implemented")
    }

    // TODO: transform here data models to domain models

}