package com.manuflowers.scrummoji.di

import android.content.Context
import com.manuflowers.scrummoji.data.local.preferences.SharePreferencesManager
import com.manuflowers.scrummoji.data.network.ApiFactory
import com.manuflowers.scrummoji.data.network.BasicAuthentication
import com.manuflowers.scrummoji.repository.JiraRepositoryImpl
import com.manuflowers.scrummoji.repository.UserRepositoryImpl
import com.manuflowers.scrummoji.ui.login.JiraLoginViewModel
import com.manuflowers.scrummoji.ui.sprintsFeed.SprintsFeedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single {
        BasicAuthentication()
    }

    single {
        ApiFactory()
    }

    single {
        JiraRepositoryImpl(get())
    }

    single { androidContext().getSharedPreferences("SCRUMMOJI_PREFERENCES", Context.MODE_PRIVATE) }

    single {
        SharePreferencesManager(get())
    }

    single {
        UserRepositoryImpl(get())
    }

    viewModel {
        JiraLoginViewModel(get(), get(), get())
    }

    viewModel {
        SprintsFeedViewModel(get(), get(), get())
    }
}