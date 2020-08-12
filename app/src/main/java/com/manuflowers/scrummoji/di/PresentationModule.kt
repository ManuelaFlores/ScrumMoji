package com.manuflowers.scrummoji.di

import com.manuflowers.scrummoji.data.network.ApiFactory
import com.manuflowers.scrummoji.data.network.BasicAuthentication
import com.manuflowers.scrummoji.repository.JiraRepositoryImpl
import com.manuflowers.scrummoji.ui.login.JiraLoginViewModel
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

    viewModel {
        JiraLoginViewModel(get(), get())
    }
}