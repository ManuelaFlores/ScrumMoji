package com.manuflowers.domain.di

import com.manuflowers.domain.sprints.GetSprintsUseCase
import com.manuflowers.domain.stories.GetStoriesUseCase
import com.manuflowers.domain.stories.UpdateStoryUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetSprintsUseCase(get()) }
    factory { GetStoriesUseCase(get()) }
    factory { UpdateStoryUseCase(get()) }
}