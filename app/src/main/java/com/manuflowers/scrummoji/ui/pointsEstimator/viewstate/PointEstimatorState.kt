package com.manuflowers.scrummoji.ui.pointsEstimator.viewstate

import com.manuflowers.scrummoji.data.model.CardEstimatorModel
import com.manuflowers.scrummoji.data.model.UserStory

sealed class PointsEstimatorState
class SuccessFirebaseResponse(
    val userStory: UserStory,
    val data: List<CardEstimatorModel>
) : PointsEstimatorState()

class PointsEstimatorError(val error: String) : PointsEstimatorState()