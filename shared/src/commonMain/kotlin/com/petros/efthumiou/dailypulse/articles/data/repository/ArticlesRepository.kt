package com.petros.efthumiou.dailypulse.articles.data.repository

import com.petros.efthumiou.dailypulse.articles.data.articles.ArticleRaw

interface ArticlesRepository {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw>
}