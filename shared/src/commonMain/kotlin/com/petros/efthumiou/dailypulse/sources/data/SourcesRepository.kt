package com.petros.efthumiou.dailypulse.sources.data

interface SourcesRepository {
    suspend fun getSources(forceFetch: Boolean): List<SourceRaw>
}