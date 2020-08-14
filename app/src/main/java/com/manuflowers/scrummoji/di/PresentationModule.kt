package com.manuflowers.scrummoji.di

import com.manuflowers.scrummoji.ui.login.JiraLoginViewModel
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { JiraLoginViewModel(get(), get(), get()) }

    viewModel { SprintsFeedViewModel(get(), get(), get()) }
}