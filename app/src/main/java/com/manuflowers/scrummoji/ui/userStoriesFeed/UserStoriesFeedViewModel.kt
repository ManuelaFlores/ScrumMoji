package com.manuflowers.scrummoji.ui.userStoriesFeed

import androidx.lifecycle.ViewModel
import com.manuflowers.scrummoji.repository.FirebaseRepository
import com.manuflowers.scrummoji.repository.UserStory

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