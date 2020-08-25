package com.manuflowers.scrummoji.data.local

import com.manuflowers.scrummoji.R
import com.manuflowers.scrummoji.data.model.CardEstimatorModel

class CardEstimatorFactory {

    fun generateCards(): List<CardEstimatorModel> {
        return listOf(
            CardEstimatorModel("1", 1, R.drawable.ic_kissing_face_emoji),
            CardEstimatorModel("2", 2, R.drawable.ic_in_love_emoji),
            CardEstimatorModel("3", 3, R.drawable.ic_start_struck_emoji),
            CardEstimatorModel("5", 5, R.drawable.ic_thinking_face_emoji),
            CardEstimatorModel("8", 8, R.drawable.ic_monocle_emoji),
            CardEstimatorModel("13", 13, R.drawable.ic_money_mouth_emoji),
            CardEstimatorModel("0", 0, R.drawable.ic_zipper_mouth_emoji),
            CardEstimatorModel("00", 0, R.drawable.ic_watermelon_emoji),
            CardEstimatorModel("000", 0, R.drawable.ic_zany_emoji)
        )
    }
}