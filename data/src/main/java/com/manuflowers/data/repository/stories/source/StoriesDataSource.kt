package com.manuflowers.data.repository.stories.source

import com.manuflowers.data.repository.sprints.model.UserCredentialData
import com.manuflowers.data.repository.stories.model.StoriesData
import com.manuflowers.data.repository.stories.model.StoryData
import com.manuflowers.data.repository.stories.model.StoryValueData
import com.manuflowers.domain.utils.Result

interface StoriesDataSource {
    suspend fun getStories(
        sprintId: Int,
        userCredentialData: UserCredentialData
    ): Result<StoriesData>

    suspend fun updateStory(
        userCredentialData: UserCredentialData,
        storyId: String,
        boardId: Int,
        storyValueData: StoryValueData
    ): Result<StoryData>
}