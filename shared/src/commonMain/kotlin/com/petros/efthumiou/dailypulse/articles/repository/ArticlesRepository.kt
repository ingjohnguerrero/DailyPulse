package com.petros.efthumiou.dailypulse.articles.repository

import com.petros.efthumiou.dailypulse.api.articles.ArticleRaw
import com.petros.efthumiou.dailypulse.api.articles.ArticlesServiceInterface
import com.petros.efthumiou.dailypulse.articles.repository.db.ArticlesDataSource

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private var service: ArticlesServiceInterface
) {
    suspend fun getArticles(): List<ArticleRaw> {
        val articlesDb = dataSource.getAllArticles()

        if(articlesDb.isEmpty()) {
            val fetchedArticles = service.fetchArticles()
            dataSource.insertArticles(fetchedArticles)
            return fetchedArticles
        }

        return articlesDb
    }
}