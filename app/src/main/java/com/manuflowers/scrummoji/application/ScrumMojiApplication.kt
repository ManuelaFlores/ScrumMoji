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
            modules(
                listOf(
                    applicationModule,
                    networkModule,
                    repositoryModule,
                    presentationModule
                )
            )
        }
    }
}