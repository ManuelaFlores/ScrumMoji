package com.manuflowers.data.repository.stories.model

import com.squareup.moshi.Json

data class IssueData(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "key") val key: String,
    @field:Json(name = "fields") val fields: FieldsData
)