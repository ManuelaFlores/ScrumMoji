package com.manuflowers.data.repository.stories

import com.manuflowers.data.repository.sprints.model.toData
import com.manuflowers.data.repository.stories.model.toData
import com.manuflowers.data.repository.stories.model.toEntity
import com.manuflowers.data.repository.stories.source.StoriesDataSource
import com.manuflowers.domain.sprints.model.UserCredentialEntity
import com.manuflowers.domain.stories.StoriesRepository
import com.manuflowers.domain.stories.model.StoriesEntity
import com.manuflowers.domain.stories.model.StoryEntity
import com.manuflowers.domain.stories.model.StoryValueEntity
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

    override suspend fun updateStory(
        userCredentialEntity: UserCredentialEntity,
        storyId: String,
        boardId: Int,
        storyValueEntity: StoryValueEntity
    ): Result<StoryEntity> {
        return when (val result = storiesDataSource.updateStory(
            userCredentialData = userCredentialEntity.toData(),
            storyId = storyId,
            boardId = boardId,
            storyValueData = storyValueEntity.toData()
        )) {
            is Result.Success -> Result.Success(result.data.toEntity())
            is Result.Error -> result
            is Result.NetworkError -> result
        }
    }
}