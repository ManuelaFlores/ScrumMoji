package com.manuflowers.scrummoji.data.model

import androidx.annotation.DrawableRes

data class CardEstimatorModel(
    val storyId: String,
    val points: Int,
    @DrawableRes val emojiResource: Int
)