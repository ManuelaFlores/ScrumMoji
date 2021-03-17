package com.manuflowers.data.di

import com.manuflowers.data.repository.sprints.SprintsRepositoryImpl
import com.manuflowers.data.repository.sprints.source.SprintsDataSource
import com.manuflowers.data.repository.sprints.source.remote.SprintsRemoteDataSource
import com.manuflowers.domain.sprints.SprintsRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<SprintsDataSource> { SprintsRemoteDataSource(get()) }

    single<SprintsRepository> { SprintsRepositoryImpl(get()) }

    /*single<SessionRemote> { SessionRemoteImpl(
        databaseReference = get()
    ) }

    single { androidContext().getSharedPreferences("SCRUMMOJI_PREFERENCES", Context.MODE_PRIVATE) }

    single { SessionRepositoryImpl(
        sessionRemote = get() ,
        sharePreferencesManager = get()
    ) }*/
}