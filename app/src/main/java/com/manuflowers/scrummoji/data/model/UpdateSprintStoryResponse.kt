package com.manuflowers.scrummoji.data.model

import com.squareup.moshi.Json

data class UpdateSprintStoryResponse(
    @field:Json(name = "fieldId") val fieldId: String,
    @field:Json(name = "value") val value: Double
)

data class UpdateSprintStoryRequest(
    @field:Json(name = "value") val value: String
)