package com.manuflowers.data.repository.stories.model

import com.manuflowers.domain.stories.model.FieldsEntity
import com.manuflowers.domain.stories.model.IssueEntity
import com.manuflowers.domain.stories.model.StoriesEntity

fun StoriesEntity.toData() = StoriesData(
    maxResults = this.maxResults,
    total = this.total,
    issues = this.issues.map { it.toData() }
)

fun IssueEntity.toData() = IssueData(
    id = this.id,
    key = this.key,
    fields = this.fields.toData()
)

fun FieldsEntity.toData() = FieldsData(
    storyPointEstimate = this.storyPointEstimate,
    storyDescription = this.storyDescription,
    storyTitle = this.storyTitle
)

fun StoriesData.toEntity() = StoriesEntity(
    maxResults = this.maxResults,
    total = this.total,
    issues = this.issues.map { it.toEntity() }
)

fun IssueData.toEntity() = IssueEntity(
    id = this.id,
    key = this.key,
    fields = this.fields.toEntity()
)

fun FieldsData.toEntity() = FieldsEntity(
    storyPointEstimate = this.storyPointEstimate ?: 0.0,
    storyDescription = this.storyDescription ?: "",
    storyTitle = this.storyTitle ?: ""
)
