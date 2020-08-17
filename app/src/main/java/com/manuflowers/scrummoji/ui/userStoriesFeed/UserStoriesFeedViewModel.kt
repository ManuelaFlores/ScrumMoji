package com.manuflowers.scrummoji.ui.userStoriesFeed

import androidx.lifecycle.ViewModel
import com.manuflowers.scrummoji.data.model.UserStory
import com.manuflowers.scrummoji.repository.FirebaseRepository

class UserStoriesFeedViewModel(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    fun generateSessionPath(onSuccessListener: (sessionPath: String) -> Unit) {
        firebaseRepository.registerNewSession { onSuccessListener.invoke(it) }
    }

    fun sendStoryToDatabase(
        userStory: UserStory,
        onSuccessListener: (sessionPath: String) -> Unit
    ) {
        firebaseRepository.sendStoryToDatabase(userStory) { onSuccessListener.invoke(it) }
    }
}