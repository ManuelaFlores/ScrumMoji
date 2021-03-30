package com.manuflowers.data.repository.stories.model

import com.manuflowers.domain.stories.model.*

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

fun StoryEntity.toData() = StoryData(
    fieldId = this.fieldId,
    value = this.value
)

fun StoryValueEntity.toData() = StoryValueData(
    value = this.value
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

fun StoryData.toEntity() = StoryEntity(
    fieldId = this.fieldId,
    value = this.value
)

fun StoryValueData.toEntity() = StoryValueEntity(
    value = this.value
)
