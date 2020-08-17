package com.manuflowers.scrummoji.ui.storyResultsEstimation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuflowers.scrummoji.data.model.Failure
import com.manuflowers.scrummoji.data.model.Success
import com.manuflowers.scrummoji.repository.FirebaseRepository
import com.manuflowers.scrummoji.ui.storyResultsEstimation.viewstate.ResultEstimationError
import com.manuflowers.scrummoji.ui.storyResultsEstimation.viewstate.ResultsEstimationState
import com.manuflowers.scrummoji.ui.storyResultsEstimation.viewstate.SuccessResultEstimationListResponse
import com.manuflowers.scrummoji.ui.storyResultsEstimation.viewstate.SuccessResultEstimationResponse

class StoryResultsEstimationViewModel(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val resultsEstimationStateMutableLiveData = MutableLiveData<ResultsEstimationState>()
    val resultsEstimationStateLiveData: LiveData<ResultsEstimationState>
        get() = resultsEstimationStateMutableLiveData

    fun listenNewEstimationListUploaded(
        storyId: String
    ) {
        firebaseRepository.getAllEstimationsUploaded(storyId) { result ->
            when (result) {
                is Success -> {
                    resultsEstimationStateMutableLiveData.postValue(
                        SuccessResultEstimationListResponse(result.data)
                    )
                }
                is Failure -> resultsEstimationStateMutableLiveData.postValue(
                    ResultEstimationError(
                        result.error.localizedMessage ?: ""
                    )
                )
            }
        }
    }

    fun listenNewEstimationUploaded(storyId: String) {
        firebaseRepository.listenNewEstimationUploaded(storyId) { result ->
            when (result) {
                is Success -> {
                    resultsEstimationStateMutableLiveData.postValue(
                        SuccessResultEstimationResponse(result.data)
                    )
                }
                is Failure -> resultsEstimationStateMutableLiveData.postValue(
                    ResultEstimationError(
                        result.error.localizedMessage ?: ""
                    )
                )
            }
        }
    }

}