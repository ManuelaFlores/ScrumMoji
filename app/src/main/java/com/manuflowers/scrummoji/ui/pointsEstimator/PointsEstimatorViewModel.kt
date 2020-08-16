package com.manuflowers.scrummoji.ui.pointsEstimator

import androidx.lifecycle.ViewModel
import com.manuflowers.scrummoji.data.model.CardEstimatorModel
import com.manuflowers.scrummoji.repository.CardEstimatorRepository
import com.manuflowers.scrummoji.repository.FirebaseRepository

class PointsEstimatorViewModel(
    private val cardEstimatorRepository: CardEstimatorRepository,
    private val firebaseRepository: FirebaseRepository
): ViewModel() {

}

sealed class PointsEstimatorState
object Loading: PointsEstimatorState()
class SuccessCardsEstimator(val data: List<CardEstimatorModel>): PointsEstimatorState()
class SuccessFirebaseResponse(): PointsEstimatorState()