package com.manuflowers.domain.stories.model

class StoriesEntity(
    val maxResults: Int,
    val total: Int,
    val issues: List<IssueEntity>
)