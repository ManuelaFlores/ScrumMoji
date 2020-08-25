package com.manuflowers.scrummoji.ui.storyPointsResultsDev

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuflowers.scrummoji.data.model.Failure
import com.manuflowers.scrummoji.data.model.Success
import com.manuflowers.scrummoji.repository.FirebaseRepository
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.ResultEstimationError
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.ResultsEstimationState
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.SuccessResultEstimationListResponse
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.SuccessResultEstimationResponse

class StoryPointsResultsDevViewModel(
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
                    Log.e("VIEWMODEL_SUCCES", "${result.data}")
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