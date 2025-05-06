package com.petros.efthumiou.dailypulse.articles.di

import com.petros.efthumiou.dailypulse.api.articles.ArticlesServiceInterface
import com.petros.efthumiou.dailypulse.api.articles.KtorArticlesService
import com.petros.efthumiou.dailypulse.articles.ArticlesUseCase
import com.petros.efthumiou.dailypulse.articles.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesServiceInterface> { KtorArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
}