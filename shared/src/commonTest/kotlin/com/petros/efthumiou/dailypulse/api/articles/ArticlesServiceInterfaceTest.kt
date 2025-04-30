package com.petros.efthumiou.dailypulse.api.articles

import kotlin.test.Test
import kotlin.test.assertEquals

class ArticlesServiceInterfaceTest {

    class FakeArticlesService : ArticlesServiceInterface {
        override suspend fun fetchArticles(): List<ArticleRaw> {
            return listOf(
                ArticleRaw(
                    title = "Test Title",
                    desc = "Test Description",
                    date = "2025-04-29T16:07:25Z",
                    imageUrl = "https://example.com/image.jpg"
                )
            )
        }
    }

    private val service = FakeArticlesService()

    @Test
    fun testFetchArticles_returnsExpectedList() = kotlinx.coroutines.test.runTest {
        val articles = service.fetchArticles()

        assertEquals(1, articles.size)
        assertEquals("Test Title", articles[0].title)
        assertEquals("Test Description", articles[0].desc)
        assertEquals("2025-04-29T16:07:25Z", articles[0].date)
        assertEquals("https://example.com/image.jpg", articles[0].imageUrl)
    }
}