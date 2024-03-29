package com.manuflowers.domain.stories

import com.manuflowers.domain.sprints.model.UserCredentialEntity
import com.manuflowers.domain.stories.model.StoriesEntity
import com.manuflowers.domain.stories.model.StoryEntity
import com.manuflowers.domain.stories.model.StoryValueEntity
import com.manuflowers.domain.utils.Result

interface StoriesRepository {
    suspend fun getStories(
        sprintId: Int,
        userCredentialEntity: UserCredentialEntity
    ): Result<StoriesEntity>

    suspend fun updateStory(
        userCredentialEntity: UserCredentialEntity,
        storyId: String,
        boardId: Int,
        storyValueEntity: StoryValueEntity
    ): Result<StoryEntity>
}