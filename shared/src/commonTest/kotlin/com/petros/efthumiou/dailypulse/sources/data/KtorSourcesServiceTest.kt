package com.petros.efthumiou.dailypulse.sources.data

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class KtorSourcesServiceTest {

    @Test
    fun testGetSourcesReturnsParsedData() = runTest {
        val mockResponse = """
            {
              "status": "ok",
              "sources": [
                {
                  "id": "abc-news",
                  "name": "ABC News",
                  "description": "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
                  "url": "https://abcnews.go.com",
                  "category": "general",
                  "language": "en",
                  "country": "us"
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

        val service = KtorSourcesService(client)
        val sources = service.fetchSources()

        assertEquals(1, sources.size)
        val source = sources[0]
        assertEquals("abc-news", source.id)
        assertEquals("ABC News", source.name)
        assertEquals("Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.", source.description)
        assertEquals("https://abcnews.go.com", source.url)
        assertEquals("general", source.category)
        assertEquals("en", source.language)
        assertEquals("us", source.country)
    }
}