package com.petros.efthumiou.dailypulse.api.articles

import com.petros.efthumiou.dailypulse.api.articles.ArticleRaw
import com.petros.efthumiou.dailypulse.api.articles.ArticlesServiceInterface
import com.petros.efthumiou.dailypulse.articles.ArticlesUseCase
import com.petros.efthumiou.dailypulse.articles.models.Article
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

class ArticlesUseCaseTest {

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

    @Test
    fun testArticleMapping() = runTest {
        val useCase = ArticlesUseCase(mockService)

        val articles: List<Article> = useCase.getArticles()

        assertEquals(3, articles.size)

        assertEquals("Title 1", articles[0].title)
        assertEquals("Description 1", articles[0].desc)
        assertTrue(articles[0].date == "Today")

        assertEquals("Click to find out more", articles[1].desc)
        assertEquals("Yesterday", articles[1].date)
        assertEquals(
            "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080",
            articles[1].imageUrl
        )

        assertEquals("3 days ago", articles[2].date)
    }
}