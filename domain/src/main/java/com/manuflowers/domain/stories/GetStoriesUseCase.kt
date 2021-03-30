package com.manuflowers.domain.stories

import com.manuflowers.domain.sprints.model.UserCredentialEntity
import com.manuflowers.domain.stories.model.StoriesEntity
import com.manuflowers.domain.utils.Result

class GetStoriesUseCase(
    private val storiesRepository: StoriesRepository
) {
    suspend fun getStories(
        sprintId: Int,
        userCredentialEntity: UserCredentialEntity
    ): Result<StoriesEntity> {
        return storiesRepository.getStories(
            sprintId = sprintId,
            userCredentialEntity = userCredentialEntity
        )
    }
}