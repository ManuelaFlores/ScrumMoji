package com.manuflowers.scrummoji.di

import android.content.Context
import com.manuflowers.scrummoji.data.local.preferences.SharePreferencesManager
import com.manuflowers.scrummoji.ui.roomLogin.RoomLoginActivity
import com.manuflowers.scrummoji.utils.CredentialsValidator
import com.manuflowers.scrummoji.utils.RoomCredentialsValidator
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {
    single { androidContext().getSharedPreferences("SCRUMMOJI_PREFERENCES", Context.MODE_PRIVATE) }

    single { SharePreferencesManager(get()) }

    single<CredentialsValidator>{ RoomCredentialsValidator() }
}