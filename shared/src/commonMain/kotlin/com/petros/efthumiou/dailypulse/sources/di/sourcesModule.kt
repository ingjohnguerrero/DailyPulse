package com.petros.efthumiou.dailypulse.sources.di

import com.petros.efthumiou.dailypulse.sources.application.SourcesUseCase
import com.petros.efthumiou.dailypulse.sources.data.SourcesService
import com.petros.efthumiou.dailypulse.sources.data.KtorSourcesService
import com.petros.efthumiou.dailypulse.sources.data.NewsApiSourcesRepository
import com.petros.efthumiou.dailypulse.sources.data.SourcesRepository
import com.petros.efthumiou.dailypulse.sources.data.db.SourcesDataSource
import com.petros.efthumiou.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {
    single<SourcesService> { KtorSourcesService(get()) }
    single<SourcesUseCase> { SourcesUseCase(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
    single<SourcesDataSource> { SourcesDataSource(get()) }
    single<SourcesRepository> { NewsApiSourcesRepository(get(), get()) }
}