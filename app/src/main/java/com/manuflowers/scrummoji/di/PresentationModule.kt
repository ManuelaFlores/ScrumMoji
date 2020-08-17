package com.manuflowers.scrummoji.di

import com.manuflowers.scrummoji.ui.jiraLogin.JiraLoginViewModel
import com.manuflowers.scrummoji.ui.pointsEstimator.PointsEstimatorViewModel
import com.manuflowers.scrummoji.ui.roomLogin.RoomLoginViewModel
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedViewModel
import com.manuflowers.scrummoji.ui.storyPointsResultsDev.StoryPointsResultsDevViewModel
import com.manuflowers.scrummoji.ui.userStoriesFeed.UserStoriesFeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { JiraLoginViewModel(get(), get(), get()) }

    viewModel { SprintsFeedViewModel(get(), get(), get()) }

    viewModel { UserStoriesFeedViewModel(get()) }

    viewModel { RoomLoginViewModel(get(), get()) }

    viewModel { PointsEstimatorViewModel(get(), get(), get()) }

    viewModel { StoryPointsResultsDevViewModel(get()) }
}