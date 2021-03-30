package com.manuflowers.data.repository.stories.model

import com.squareup.moshi.Json

data class StoriesData(
    @field:Json(name = "maxResults") val maxResults: Int,
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "issues") val issues: List<IssueData>
)