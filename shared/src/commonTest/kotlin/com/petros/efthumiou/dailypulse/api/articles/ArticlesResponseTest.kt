package com.petros.efthumiou.dailypulse.api.articles

import com.petros.efthumiou.dailypulse.articles.data.articles.ArticleRaw
import com.petros.efthumiou.dailypulse.articles.data.articles.ArticlesResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ArticlesResponseTest {
    private val json = Json { ignoreUnknownKeys = true }

    private val sampleJson = """
        {
            "status": "ok",
            "totalResults": 1,
            "articles": [
                {
                    "title": "Test Article",
                    "description": "Article description",
                    "publishedAt": "2024-04-01T10:00:00Z",
                    "urlToImage": "https://example.com/image.jpg"
                }
            ]
        }
    """.trimIndent()

    @Test
    fun `should deserialize JSON into ArticlesResponse`() {
        val response = json.decodeFromString<ArticlesResponse>(sampleJson)

        assertEquals("ok", response.status)
        assertEquals(1, response.results)
        assertEquals(1, response.articles.size)

        val article = response.articles.first()
        assertEquals("Test Article", article.title)
        assertEquals("Article description", article.desc)
        assertEquals("2024-04-01T10:00:00Z", article.date)
        assertEquals("https://example.com/image.jpg", article.imageUrl)
    }

    @Test
    fun `should serialize ArticlesResponse to JSON`() {
        val response = ArticlesResponse(
            status = "ok",
            results = 1,
            articles = listOf(
                ArticleRaw(
                    title = "Serialized Title",
                    desc = "Serialized description",
                    date = "2024-04-02T11:00:00Z",
                    imageUrl = "https://example.com/serialized.jpg"
                )
            )
        )

        val jsonString = json.encodeToString(response)

        assertTrue(jsonString.contains("\"status\":\"ok\""))
        assertTrue(jsonString.contains("\"totalResults\":1"))
        assertTrue(jsonString.contains("\"title\":\"Serialized Title\""))
        assertTrue(jsonString.contains("\"description\":\"Serialized description\""))
    }
}