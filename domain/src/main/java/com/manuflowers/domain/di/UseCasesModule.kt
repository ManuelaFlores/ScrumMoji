package com.manuflowers.domain.di

import com.manuflowers.domain.sprints.GetSprintsUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetSprintsUseCase(get()) }
}