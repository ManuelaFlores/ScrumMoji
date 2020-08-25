package com.manuflowers.scrummoji.data.model

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
@IgnoreExtraProperties
data class UserStory(
    @PropertyName("title") var title: String = "",
    @PropertyName("id") var id: String = ""
) : Parcelable

@IgnoreExtraProperties
data class StoryPointEstimation(
    @PropertyName("userNickname") val userNickname: String = "",
    @PropertyName("storyId") val storyId: String = "",
    @PropertyName("storyPoints") var storyPoints: Int = 0
)