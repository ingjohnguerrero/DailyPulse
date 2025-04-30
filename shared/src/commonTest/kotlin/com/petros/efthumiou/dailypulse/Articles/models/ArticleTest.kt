package com.petros.efthumiou.dailypulse.Articles.models

import com.petros.efthumiou.dailypulse.articles.models.Article
import kotlin.test.Test
import kotlin.test.assertEquals

class ArticleTest {

    @Test
    fun `articles with same properties should be equal`() {
        val a1 = Article("Title", "Desc", "2024-01-01", "https://image.url")
        val a2 = Article("Title", "Desc", "2024-01-01", "https://image.url")

        assertEquals(a1, a2)
    }

    @Test
    fun `copy should create a new instance with overridden field`() {
        val original = Article("Title", "Desc", "2024-01-01", "https://image.url")
        val copy = original.copy(title = "New Title")

        assertEquals("New Title", copy.title)
        assertEquals(original.desc, copy.desc)
    }
}