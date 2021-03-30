package com.manuflowers.domain.stories.model

data class StoriesEntity(
    val maxResults: Int,
    val total: Int,
    val issues: List<IssueEntity>
)