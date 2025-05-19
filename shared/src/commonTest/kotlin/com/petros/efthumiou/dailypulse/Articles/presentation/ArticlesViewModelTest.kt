package com.petros.efthumiou.dailypulse.articles.presentation

import com.petros.efthumiou.dailypulse.articles.application.models.ArticlesUseCase
import com.petros.efthumiou.dailypulse.articles.data.articles.ArticleRaw
import com.petros.efthumiou.dailypulse.articles.data.articles.ArticlesServiceInterface
import com.petros.efthumiou.dailypulse.articles.data.repository.ArticlesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class ArticlesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val mockService = object : ArticlesServiceInterface {
        override suspend fun fetchArticles(): List<ArticleRaw> {
            val now = Clock.System.now()
            val yesterday = now.minus(DateTimePeriod(days = 1), TimeZone.UTC)
            val threeDaysAgo = now.minus(DateTimePeriod(days = 3), TimeZone.UTC)

            return listOf(
                ArticleRaw("Title 1", "Description 1", now.toString(), "https://test.com/image1.png"),
                ArticleRaw("Title 2", null, yesterday.toString(), null),
                ArticleRaw("Title 3", "Description 3", threeDaysAgo.toString(), "https://test.com/image3.png")
            )
        }
    }

    private val mockRepository: ArticlesRepository = object : ArticlesRepository {
        override suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
            return mockService.fetchArticles()
        }
    }

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testFetchArticlesUpdatesState() = runTest {
        val mockUseCase = ArticlesUseCase(mockRepository)
        val viewModel = ArticlesViewModel(mockUseCase)

        // Advance time to simulate delay
        advanceTimeBy(500)

        // Await any remaining coroutine work
        advanceUntilIdle()

        val state = viewModel.articlesState.value
        assertFalse(state.loading)
        assertEquals(3, state.articles.size)
    }
}
