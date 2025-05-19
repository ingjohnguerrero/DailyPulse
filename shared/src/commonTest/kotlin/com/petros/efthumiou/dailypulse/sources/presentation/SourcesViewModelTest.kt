package com.petros.efthumiou.dailypulse.sources.presentation

import com.petros.efthumiou.dailypulse.sources.data.SourceRaw
import com.petros.efthumiou.dailypulse.sources.data.SourcesService
import com.petros.efthumiou.dailypulse.sources.application.SourcesUseCase
import com.petros.efthumiou.dailypulse.sources.data.SourcesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class SourcesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val mockService = object : SourcesService {
        override suspend fun fetchSources(): List<SourceRaw> {
            return listOf(
                SourceRaw(
                    id = "abc-news",
                    name = "ABC News",
                    description = "Your trusted source for breaking news.",
                    url = "https://abcnews.go.com",
                    category = "general",
                    language = "en",
                    country = "us"
                ),
                SourceRaw(
                    id = "bbc-news",
                    name = "BBC News",
                    description = "Up-to-the-minute news.",
                    url = "https://bbc.co.uk/news",
                    category = "general",
                    language = "en",
                    country = "gb"
                )
            )
        }
    }

    private val mockRepository: SourcesRepository = object : SourcesRepository {
        override suspend fun getSources(forceFetch: Boolean): List<SourceRaw> {
            return mockService.fetchSources()
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
    fun testGetSourcesUpdatesState() = runTest {
        val mockUseCase = SourcesUseCase(mockRepository)
        val viewModel = SourcesViewModel(mockUseCase)

        // Advance time to simulate delay
        advanceTimeBy(500)

        // Await any remaining coroutine work
        advanceUntilIdle()

        val state = viewModel.sourcesState.value
        assertFalse(state.loading)
        assertEquals(2, state.sources.size)
        assertEquals("abc-news", state.sources[0].id)
        assertEquals("bbc-news", state.sources[1].id)
    }
}