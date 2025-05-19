package com.petros.efthumiou.dailypulse.sources.application

import kotlin.test.Test
import kotlin.test.assertEquals

class SourceTest {
    // Add @Test for Source data class
    @Test
    fun testSource() {
        val source = Source(
            id = "1",
            name = "Test Source",
            description = "This is a test source",
            url = "https://testsource.com",
            category = "general",
            language = "en",
            country = "us"
        )

        // Use assertEquals to check the properties of the Source object


        // Check that the properties are set correctly
        // Use assertEquals to check the properties of the Source object
        assertEquals(source.id, "1")
        assertEquals(source.name, "Test Source")
        assertEquals(source.description, "This is a test source")
        assertEquals(source.url, "https://testsource.com")
        assertEquals(source.category, "general")
        assertEquals(source.language, "en")
        assertEquals(source.country, "us")
    }
}