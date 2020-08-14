package com.manuflowers.scrummoji.data.model

import android.os.Parcelable
import com.manuflowers.scrummoji.repository.UserStory
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SprintStoriesResponse(
    @field:Json(name = "maxResults") val maxResults: Int,
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "issues") val issues: List<IssueResponse>
) : Parcelable

@Parcelize
data class IssueResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "key") val key: String,
    @field:Json(name = "fields") val fields: Fields
) : Parcelable

@Parcelize
data class Fields(
    @field:Json(name = "customfield_10016") val storyPointEstimate: Double?,
    @field:Json(name = "summary") val storyTitle: String?,
    @field:Json(name = "description") val storyDescription: String?
) : Parcelable

fun IssueResponse.asUserStoryModel(): UserStory {
    return this.run {
        UserStory(
            id = this.id,
            title = this.fields.storyTitle ?: ""
        )
    }
}