package com.manuflowers.data.repository.stories.model

import com.squareup.moshi.Json

data class StoryValueData(
    @field:Json(name = "value") val value: String
)