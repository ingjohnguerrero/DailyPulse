package com.petros.efthumiou.dailypulse.sources.application

import com.petros.efthumiou.dailypulse.sources.data.SourceRaw
import com.petros.efthumiou.dailypulse.sources.data.SourcesRepository

class SourcesUseCase(private val repository: SourcesRepository) {

    suspend fun getSources(forceFetch: Boolean): List<Source> {
        val sources = repository.getSources(forceFetch)
        return mapSourcesToDomain(sources)
    }

    private fun mapSourcesToDomain(sources: List<SourceRaw>): List<Source> {
        return sources.map { source ->
            Source(
                id = source.id,
                name = source.name,
                description = source.description,
                url = source.url,
                category = source.category,
                language = source.language,
                country = source.country,
                locale = getLocaleString(source.language, source.country)
            )
        }
    }

    private fun getLocaleString(language: String, country: String): String {
        return "$language-$country"
    }
}