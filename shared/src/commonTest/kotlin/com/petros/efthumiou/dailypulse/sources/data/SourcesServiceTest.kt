package com.petros.efthumiou.dailypulse.sources.data
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SourcesServiceInterfaceTest {

    private class FakeSourcesService : SourcesService {
        override suspend fun fetchSources(): List<SourceRaw> {
            return listOf(
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
        }
    }

    private val service = FakeSourcesService()

    @Test
    fun testGetSources_returnsExpectedResponse() = runTest {
        val response = service.fetchSources()

        val source = response[0]
        assertEquals("abc-news", source.id)
        assertEquals("ABC News", source.name)
        assertEquals("Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.", source.description)
        assertEquals("https://abcnews.go.com", source.url)
        assertEquals("general", source.category)
        assertEquals("en", source.language)
        assertEquals("us", source.country)
    }
}