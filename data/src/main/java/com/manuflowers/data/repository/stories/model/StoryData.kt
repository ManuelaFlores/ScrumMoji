package com.manuflowers.data.repository.stories.model

import com.squareup.moshi.Json

data class StoryData(
    @field:Json(name = "fieldId") val fieldId: String,
    @field:Json(name = "value") val value: Double
)