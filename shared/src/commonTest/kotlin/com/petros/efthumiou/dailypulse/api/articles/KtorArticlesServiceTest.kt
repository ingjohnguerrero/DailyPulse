package com.petros.efthumiou.dailypulse.api.articles

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class KtorArticlesServiceTest {

    @Test
    fun testFetchArticlesReturnsParsedData() = runTest {
        val mockResponse = """
            {
              "status": "ok",
              "totalResults": 1,
              "articles": [
                {
                  "title": "Sample Title",
                  "description": "Sample description",
                  "publishedAt": "2024-04-01",
                  "urlToImage": "https://example.com/image.jpg"
                }
              ]
            }
        """.trimIndent()

        val mockEngine = MockEngine { request ->
            respond(
                content = mockResponse,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        val service = KtorArticlesService(client)
        val articles = service.fetchArticles()

        assertEquals(1, articles.size)
        assertEquals("Sample Title", articles[0].title)
    }
}