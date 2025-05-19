package com.petros.efthumiou.dailypulse.sources.data

interface SourcesService {
    suspend fun fetchSources(): List<SourceRaw>
}