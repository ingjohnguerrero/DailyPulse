package com.petros.efthumiou.dailypulse.articles.data.articles

interface ArticlesServiceInterface {
    suspend fun fetchArticles(): List<ArticleRaw>
}