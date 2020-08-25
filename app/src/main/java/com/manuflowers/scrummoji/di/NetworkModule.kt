package com.manuflowers.scrummoji.di

import com.manuflowers.scrummoji.data.network.ApiFactory
import com.manuflowers.scrummoji.data.network.BasicAuthentication
import org.koin.dsl.module

val networkModule = module {
    single { BasicAuthentication() }

    single { ApiFactory() }
}