package com.manuflowers.scrummoji.ui.sprintsFeed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuflowers.scrummoji.data.model.Failure
import com.manuflowers.scrummoji.data.model.Success
import com.manuflowers.scrummoji.data.network.BasicAuthentication
import com.manuflowers.scrummoji.repository.JiraRepositoryImpl
import com.manuflowers.scrummoji.repository.UserRepositoryImpl
import com.manuflowers.scrummoji.ui.sprintsFeed.viewstate.SprintsFeed
import com.manuflowers.scrummoji.ui.sprintsFeed.viewstate.SprintsFeedError
import com.manuflowers.scrummoji.ui.sprintsFeed.viewstate.SprintsFeedSuccess
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SprintsFeedViewModel(
    private val repository: JiraRepositoryImpl,
    private val userRepositoryImpl: UserRepositoryImpl,
    private val basicAuthentication: BasicAuthentication
) : ViewModel() {

    private val sprintsMutableLiveData = MutableLiveData<SprintsFeed>()
    val sprintsLiveData: LiveData<SprintsFeed>
        get() = sprintsMutableLiveData

    fun getSprintStories(sprintId: Int) {
        viewModelScope.launch {
            basicAuthentication.createCredentials(userRepositoryImpl.getUserCredentials())
            repository.getSprintStories(sprintId = sprintId, interceptor = basicAuthentication)
            when (val result = repository.getSprintStories(
                sprintId = sprintId,
                interceptor = basicAuthentication
            )) {
                is Success -> {
                    result.data.collect {
                        sprintsMutableLiveData.postValue(SprintsFeedSuccess(it))
                    }
                }
                is Failure -> {
                    sprintsMutableLiveData.postValue(
                        SprintsFeedError(
                            result.error.localizedMessage ?: ""
                        )
                    )
                }
            }
        }
    }
}