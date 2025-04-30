package com.petros.efthumiou.dailypulse.api.articles

interface ArticlesServiceInterface {
    suspend fun fetchArticles(): List<ArticleRaw>
}