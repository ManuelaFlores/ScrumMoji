package com.manuflowers.data.repository.stories

import com.manuflowers.data.repository.sprints.model.toData
import com.manuflowers.data.repository.stories.model.toEntity
import com.manuflowers.data.repository.stories.source.StoriesDataSource
import com.manuflowers.domain.sprints.model.UserCredentialEntity
import com.manuflowers.domain.stories.StoriesRepository
import com.manuflowers.domain.stories.model.StoriesEntity
import com.manuflowers.domain.utils.Result

class StoriesRepositoryImpl(
    private val storiesDataSource: StoriesDataSource
) : StoriesRepository {
    override suspend fun getStories(
        sprintId: Int,
        userCredentialEntity: UserCredentialEntity
    ): Result<StoriesEntity> {
        return when (val result = storiesDataSource.getStories(
            sprintId = sprintId,
            userCredentialData = userCredentialEntity.toData()
        )) {
            is Result.Success -> Result.Success(result.data.toEntity())
            is Result.Error -> result
            is Result.NetworkError -> result
        }
    }
}