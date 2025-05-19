package com.petros.efthumiou.dailypulse.sources.data
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class SourcesRawTest {

    @Test
    fun `test source raw from json`() {
        val jsonContent = """
            {
                "id": "abc-news",
                "name": "ABC News",
                "description": "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
                "url": "https://abcnews.go.com",
                "category": "general",
                "language": "en",
                "country": "us"
            }
        """
        val source = Json.decodeFromString<SourceRaw>(jsonContent)

        assertNotNull(source)
        assertEquals("abc-news", source.id)
        assertEquals("ABC News", source.name)
        assertEquals("Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.", source.description)
        assertEquals("https://abcnews.go.com", source.url)
    }
}