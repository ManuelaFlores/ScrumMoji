package com.manuflowers.domain.stories

import com.manuflowers.domain.sprints.model.UserCredentialEntity
import com.manuflowers.domain.stories.model.StoryEntity
import com.manuflowers.domain.stories.model.StoryValueEntity
import com.manuflowers.domain.utils.Result

class UpdateStoryUseCase(
    private val storiesRepository: StoriesRepository
) {
    suspend fun updateStory(
        userCredentialEntity: UserCredentialEntity,
        storyId: String,
        boardId: Int,
        storyValueEntity: StoryValueEntity
    ): Result<StoryEntity> {
        return storiesRepository.updateStory(
            userCredentialEntity, storyId, boardId, storyValueEntity
        )
    }
}