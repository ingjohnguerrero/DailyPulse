package com.petros.efthumiou.dailypulse.sources.data
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class SourcesResponseTest {

    private val json = Json { ignoreUnknownKeys = true }

    private val sampleJson = """
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

    @Test
    fun `should deserialize JSON into SourcesResponse`() {
        val response = json.decodeFromString<SourcesResponse>(sampleJson)

        assertNotNull(response)
        assertEquals("ok", response.status)
        assertEquals(1, response.sources.size)

        val source = response.sources.first()
        assertEquals("abc-news", source.id)
        assertEquals("ABC News", source.name)
        assertEquals("Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.", source.description)
        assertEquals("https://abcnews.go.com", source.url)
        assertEquals("general", source.category)
        assertEquals("en", source.language)
        assertEquals("us", source.country)
    }

    @Test
    fun `should serialize SourcesResponse to JSON`() {
        val response = SourcesResponse(
            status = "ok",
            sources = listOf(
                SourceRaw(
                    id = "abc-news",
                    name = "ABC News",
                    description = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
                    url = "https://abcnews.go.com",
                    category = "general",
                    language = "en",
                    country = "us"
                )
            )
        )

        val jsonString = json.encodeToString(response)

        assertTrue(jsonString.contains("\"status\":\"ok\""))
        assertTrue(jsonString.contains("\"id\":\"abc-news\""))
        assertTrue(jsonString.contains("\"name\":\"ABC News\""))
        assertTrue(jsonString.contains("\"description\":\"Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.\""))
    }
}