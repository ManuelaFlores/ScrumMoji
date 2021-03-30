package com.manuflowers.data.repository.stories.source

import com.manuflowers.data.repository.sprints.model.UserCredentialData
import com.manuflowers.data.repository.stories.model.StoriesData
import com.manuflowers.domain.utils.Result

interface StoriesDataSource {
    suspend fun getStories(
        sprintId: Int,
        userCredentialData: UserCredentialData
    ): Result<StoriesData>
}