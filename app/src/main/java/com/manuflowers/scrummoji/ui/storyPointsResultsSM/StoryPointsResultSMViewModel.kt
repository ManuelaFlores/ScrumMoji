package com.manuflowers.scrummoji.ui.storyPointsResultsSM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuflowers.scrummoji.data.model.Failure
import com.manuflowers.scrummoji.data.model.Success
import com.manuflowers.scrummoji.data.model.UpdateSprintStoryRequest
import com.manuflowers.scrummoji.data.network.BasicAuthentication
import com.manuflowers.scrummoji.repository.FirebaseRepository
import com.manuflowers.scrummoji.repository.JiraRepositoryImpl
import com.manuflowers.scrummoji.repository.UserRepositoryImpl
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.ResultEstimationError
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.ResultsEstimationState
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.SuccessResultEstimationResponse
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.viewstate.UpdateUSerStoryStateSuccess
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StoryPointsResultSMViewModel(
    private val firebaseRepository: FirebaseRepository,
    private val userRepositoryImpl: UserRepositoryImpl,
    private val jiraRepositoryImpl: JiraRepositoryImpl,
    private val basicAuthentication: BasicAuthentication
) : ViewModel() {


    private val resultsEstimationStateMutableLiveData = MutableLiveData<ResultsEstimationState>()
    val resultsEstimationStateLiveData: LiveData<ResultsEstimationState>
        get() = resultsEstimationStateMutableLiveData

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

    fun updateStory(pointsSelected: String, storyId: String) {
        viewModelScope.launch {
            basicAuthentication.createCredentials(userRepositoryImpl.getUserCredentials())

            val result = jiraRepositoryImpl.updateSprintStory(
                updateSprintStoryRequest = UpdateSprintStoryRequest(pointsSelected),
                storyId = storyId,
                interceptor = basicAuthentication
            )

            when (result) {
                is Success -> {
                    result.data.collect {
                        resultsEstimationStateMutableLiveData.postValue(
                            UpdateUSerStoryStateSuccess(
                                it.value.toInt().toString()
                            )
                        )
                    }
                }
                is Failure -> {
                    ResultEstimationError(result.error.localizedMessage ?: "")
                }
            }

        }
    }
}