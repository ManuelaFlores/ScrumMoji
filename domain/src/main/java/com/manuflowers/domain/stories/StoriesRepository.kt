package com.manuflowers.domain.stories

import com.manuflowers.domain.sprints.model.UserCredentialEntity
import com.manuflowers.domain.stories.model.StoriesEntity
import com.manuflowers.domain.utils.Result

interface StoriesRepository {
    suspend fun getStories(
        sprintId: Int,
        userCredentialEntity: UserCredentialEntity
    ): Result<StoriesEntity>
}