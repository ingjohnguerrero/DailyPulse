package com.petros.efthumiou.dailypulse.articles

import com.petros.efthumiou.dailypulse.articles.models.Article

data class ArticlesState(
    val articles: List<Article> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)
