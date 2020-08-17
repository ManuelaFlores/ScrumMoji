package com.manuflowers.scrummoji.ui.pointsEstimator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuflowers.scrummoji.data.model.CardEstimatorModel
import com.manuflowers.scrummoji.data.model.Failure
import com.manuflowers.scrummoji.data.model.Success
import com.manuflowers.scrummoji.repository.*
import com.manuflowers.scrummoji.ui.pointsEstimator.viewstate.PointsEstimatorError
import com.manuflowers.scrummoji.ui.pointsEstimator.viewstate.PointsEstimatorState
import com.manuflowers.scrummoji.ui.pointsEstimator.viewstate.SuccessFirebaseResponse

class PointsEstimatorViewModel(
    private val cardEstimatorRepository: CardEstimatorRepository,
    private val firebaseRepository: FirebaseRepository,
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {

    private val pointsEstimatorStateMutableLiveData = MutableLiveData<PointsEstimatorState>()
    val pointsEstimatorStateLiveData: LiveData<PointsEstimatorState>
        get() = pointsEstimatorStateMutableLiveData

    private var currentCardEstimatorModel = CardEstimatorModel("", 0, 0)
    private var currentStoryId = ""

    fun listenForNewStory() {
        firebaseRepository.listenNewStoriesUploaded {
            when (it) {
                is Success -> {
                    pointsEstimatorStateMutableLiveData.postValue(
                        SuccessFirebaseResponse(it.data, cardEstimatorRepository.getCards())
                    )
                }
                is Failure -> pointsEstimatorStateMutableLiveData.postValue(
                    PointsEstimatorError(
                        it.error.localizedMessage ?: ""
                    )
                )
            }
        }
    }

    fun setCurrentStoryId(storyId: String) {
        this.currentStoryId = storyId
    }

    fun updateCurrentCardEstimatorModel(cardEstimatorModel: CardEstimatorModel) {
        this.currentCardEstimatorModel = cardEstimatorModel
    }

    fun uploadEstimatedStory(onSuccessListener: () -> Unit) {
        firebaseRepository.sendStoryEstimationToDatabase(
            StoryPointEstimation(
                userNickname = userRepositoryImpl.getDeveloperNickname() ?: "",
                storyId = currentStoryId,
                storyPoints = currentCardEstimatorModel.points
            )
        ) { onSuccessListener.invoke() }
    }

}