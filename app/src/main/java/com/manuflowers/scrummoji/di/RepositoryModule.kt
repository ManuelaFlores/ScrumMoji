package com.manuflowers.scrummoji.di

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.manuflowers.scrummoji.data.local.CardEstimatorFactory
import com.manuflowers.scrummoji.repository.CardEstimatorRepository
import com.manuflowers.scrummoji.repository.FirebaseRepository
import com.manuflowers.scrummoji.repository.JiraRepositoryImpl
import com.manuflowers.scrummoji.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { JiraRepositoryImpl(get()) }

    single { UserRepositoryImpl(get()) }

    single { Firebase.database.reference }

    single { FirebaseRepository(get(), get()) }

    single { CardEstimatorFactory() }

    single { CardEstimatorRepository(get()) }
}