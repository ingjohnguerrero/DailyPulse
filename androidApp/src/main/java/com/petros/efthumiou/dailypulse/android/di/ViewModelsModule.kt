package com.petros.efthumiou.dailypulse.android.di

import com.petros.efthumiou.dailypulse.articles.presentation.ArticlesViewModel
import com.petros.efthumiou.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { ArticlesViewModel(get()) }
    viewModel { SourcesViewModel(get()) }
}