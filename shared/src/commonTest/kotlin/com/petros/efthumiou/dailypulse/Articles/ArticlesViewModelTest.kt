package com.petros.efthumiou.dailypulse

import com.petros.efthumiou.dailypulse.articles.ArticlesViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class ArticlesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

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
        val viewModel = ArticlesViewModel()

        // Advance time to simulate delay
        advanceTimeBy(500)

        // Await any remaining coroutine work
        advanceUntilIdle()

        val state = viewModel.articlesState.value
        assertFalse(state.loading)
        assertEquals(3, state.articles.size)
    }
}
