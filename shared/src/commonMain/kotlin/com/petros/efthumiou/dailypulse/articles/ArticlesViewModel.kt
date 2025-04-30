package com.petros.efthumiou.dailypulse.articles

import com.petros.efthumiou.dailypulse.BaseViewModel
import com.petros.efthumiou.dailypulse.api.articles.KtorArticlesService
import com.petros.efthumiou.dailypulse.articles.models.Article
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel: BaseViewModel() {

    private var _articlesState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    private val useCase: ArticlesUseCase

    init {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        val service = KtorArticlesService(httpClient)
        useCase = ArticlesUseCase(service)
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val fetchedArticles = useCase.getArticles()

            _articlesState.emit(ArticlesState(articles = fetchedArticles))
        }
    }
}