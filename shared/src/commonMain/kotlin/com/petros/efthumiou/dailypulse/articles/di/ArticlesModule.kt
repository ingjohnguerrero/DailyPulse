package com.petros.efthumiou.dailypulse.articles.di

import com.petros.efthumiou.dailypulse.articles.data.articles.ArticlesServiceInterface
import com.petros.efthumiou.dailypulse.articles.data.articles.KtorArticlesService
import com.petros.efthumiou.dailypulse.articles.application.models.ArticlesUseCase
import com.petros.efthumiou.dailypulse.articles.presentation.ArticlesViewModel
import com.petros.efthumiou.dailypulse.articles.repository.ArticlesRepository
import com.petros.efthumiou.dailypulse.articles.repository.db.ArticlesDataSource
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesServiceInterface> { KtorArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesDataSource> { ArticlesDataSource(get()) }
    single<ArticlesRepository> { ArticlesRepository(get(), get()) }
}