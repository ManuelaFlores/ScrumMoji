package com.manuflowers.data.di

import com.manuflowers.data.repository.sprints.SprintsRepositoryImpl
import com.manuflowers.data.repository.sprints.source.SprintsDataSource
import com.manuflowers.data.repository.sprints.source.remote.SprintsRemoteDataSource
import com.manuflowers.data.repository.stories.StoriesRepositoryImpl
import com.manuflowers.data.repository.stories.source.StoriesDataSource
import com.manuflowers.data.repository.stories.source.remote.StoriesRemoteDataSource
import com.manuflowers.domain.sprints.SprintsRepository
import com.manuflowers.domain.stories.StoriesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<SprintsDataSource> { SprintsRemoteDataSource(get()) }

    single<SprintsRepository> { SprintsRepositoryImpl(get()) }

    single<StoriesDataSource> { StoriesRemoteDataSource(get()) }

    single<StoriesRepository> { StoriesRepositoryImpl(get()) }

    /*single<SessionRemote> { SessionRemoteImpl(
        databaseReference = get()
    ) }

    single { androidContext().getSharedPreferences("SCRUMMOJI_PREFERENCES", Context.MODE_PRIVATE) }

    single { SessionRepositoryImpl(
        sessionRemote = get() ,
        sharePreferencesManager = get()
    ) }*/
}