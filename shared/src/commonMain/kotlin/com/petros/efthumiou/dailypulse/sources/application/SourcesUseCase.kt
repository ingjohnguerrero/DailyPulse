package com.petros.efthumiou.dailypulse.sources.application

import com.petros.efthumiou.dailypulse.sources.data.SourceRaw
import com.petros.efthumiou.dailypulse.sources.data.SourcesRepository

class SourcesUseCase {
    private val repository: SourcesRepository

    constructor(repository: SourcesRepository) {
        this.repository = repository
    }

    suspend fun getSources(forceFetch: Boolean): List<Source> {
        return repository.getSources(forceFetch).map { sourceRaw ->
            Source(
                id = sourceRaw.id,
                name = sourceRaw.name,
                description = sourceRaw.description,
                url = sourceRaw.url,
                category = sourceRaw.category,
                language = sourceRaw.language,
                country = sourceRaw.country
            )
        }
    }
}