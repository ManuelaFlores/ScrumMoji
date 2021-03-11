package com.manuflowers.data.di

import android.content.Context
import com.manuflowers.data.remote.session.SessionRemote
import com.manuflowers.data.remote.session.SessionRemoteImpl
import com.manuflowers.data.repository.session.SessionRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    single<SessionRemote> { SessionRemoteImpl(
        databaseReference = get()
    ) }

    single { androidContext().getSharedPreferences("SCRUMMOJI_PREFERENCES", Context.MODE_PRIVATE) }

    single { SessionRepositoryImpl(
        sessionRemote = get() ,
        sharePreferencesManager = get()
    ) }
}