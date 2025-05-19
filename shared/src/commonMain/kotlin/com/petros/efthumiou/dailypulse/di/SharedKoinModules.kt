package com.petros.efthumiou.dailypulse.di

import com.petros.efthumiou.dailypulse.articles.di.articlesModule
import com.petros.efthumiou.dailypulse.sources.di.sourcesModule

val sharedKoinModules = listOf(
    articlesModule,
    sourcesModule,
    networkModule
)