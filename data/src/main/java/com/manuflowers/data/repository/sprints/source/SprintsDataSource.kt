package com.manuflowers.data.repository.sprints.source

import com.manuflowers.data.repository.sprints.model.SprintsData
import com.manuflowers.data.repository.sprints.model.UserCredentialData
import com.manuflowers.domain.utils.Result

interface SprintsDataSource {
    suspend fun getSprints(userCredentialData: UserCredentialData): Result<SprintsData>
}