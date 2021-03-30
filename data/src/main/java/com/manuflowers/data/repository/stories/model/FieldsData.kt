package com.manuflowers.data.repository.stories.model

import com.squareup.moshi.Json

data class FieldsData(
    @field:Json(name = "customfield_10016") val storyPointEstimate: Double?,
    @field:Json(name = "summary") val storyTitle: String?,
    @field:Json(name = "description") val storyDescription: String?
)