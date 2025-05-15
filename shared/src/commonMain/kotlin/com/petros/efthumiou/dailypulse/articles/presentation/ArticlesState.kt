package com.petros.efthumiou.dailypulse.articles.presentation

import com.petros.efthumiou.dailypulse.articles.application.models.Article

data class ArticlesState(
    val articles: List<Article> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)
