package com.manuflowers.scrummoji.repository

import com.manuflowers.scrummoji.data.local.CardEstimatorFactory

class CardEstimatorRepository(
    private val cardEstimatorFactory: CardEstimatorFactory
) {
    fun getCards() = cardEstimatorFactory.generateCards()
}