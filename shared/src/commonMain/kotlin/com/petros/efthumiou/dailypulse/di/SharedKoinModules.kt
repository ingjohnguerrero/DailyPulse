package com.petros.efthumiou.dailypulse.di

import com.petros.efthumiou.dailypulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    articlesModule,
    networkModule
)