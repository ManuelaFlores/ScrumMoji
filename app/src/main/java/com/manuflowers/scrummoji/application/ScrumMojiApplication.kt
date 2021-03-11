package com.manuflowers.scrummoji.application

import android.app.Application
import com.manuflowers.scrummoji.di.applicationModule
import com.manuflowers.scrummoji.di.networkModule
import com.manuflowers.scrummoji.di.presentationModule
import com.manuflowers.scrummoji.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ScrumMojiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ScrumMojiApplication)
            modules(appModules + repositoryModules)
        }
    }
}

val appModules = listOf(
    applicationModule,
    networkModule,
    repositoryModule,
    presentationModule
)

val repositoryModules = listOf(
    com.manuflowers.data.di.repositoryModule,
    com.manuflowers.data.di.networkModule
)

