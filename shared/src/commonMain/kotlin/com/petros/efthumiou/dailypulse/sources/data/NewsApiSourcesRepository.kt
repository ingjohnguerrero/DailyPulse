package com.petros.efthumiou.dailypulse.sources.data

class NewsApiSourcesRepository(
    private val newsApiService: SourcesService,
) : SourcesRepository {

    override suspend fun getSources(forceFetch: Boolean): List<SourceRaw> {
        return newsApiService.fetchSources()
    }
}