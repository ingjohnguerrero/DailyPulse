package com.petros.efthumiou.dailypulse.articles.repository

import com.petros.efthumiou.dailypulse.articles.data.articles.ArticleRaw
import com.petros.efthumiou.dailypulse.articles.data.articles.ArticlesServiceInterface
import com.petros.efthumiou.dailypulse.articles.repository.db.ArticlesDataSource

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private var service: ArticlesServiceInterface
) {
    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
        if (forceFetch) {
            dataSource.clearArticles()
            return fetchArticles()
        }

        val articlesDb = dataSource.getAllArticles()
        println("Got ${articlesDb.size} from the database!!!")

        if(articlesDb.isEmpty()) {
            return fetchArticles()
        }

        return articlesDb
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchedArticles = service.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}