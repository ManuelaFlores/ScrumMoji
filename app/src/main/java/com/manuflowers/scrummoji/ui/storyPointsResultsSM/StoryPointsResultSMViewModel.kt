package com.manuflowers.scrummoji.ui.storyPointsResultsSM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuflowers.scrummoji.data.model.Failure
import com.manuflowers.scrummoji.data.model.Success
import com.manuflowers.scrummoji.repository.FirebaseRepository
import com.manuflowers.scrummoji.repository.UserRepositoryImpl
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.ResultEstimationError
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.ResultsEstimationState
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.SuccessResultEstimationResponse

class StoryPointsResultSMViewModel(
    private val firebaseRepository: FirebaseRepository,
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {


    private val resultsEstimationStateMutableLiveData = MutableLiveData<ResultsEstimationState>()
    val resultsEstimationStateLiveData: LiveData<ResultsEstimationState>
        get() = resultsEstimationStateMutableLiveData

    fun listenNewEstimationUploaded(storyId: String) {
        Log.e("VIEW_MODEL", "${userRepositoryImpl.getRoomId()}")
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