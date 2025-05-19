package com.petros.efthumiou.dailypulse.sources.data

import com.petros.efthumiou.dailypulse.sources.data.db.SourcesDataSource

class NewsApiSourcesRepository(
    private val newsApiService: SourcesService,
    private var dataSource: SourcesDataSource
) : SourcesRepository {

    override suspend fun getSources(forceFetch: Boolean): List<SourceRaw> {
        if (forceFetch) {
            return fetchSources()
        }

        val sourcesDb = dataSource.getAllSources()
        println("Got ${sourcesDb.size} from the database!!!")

        if (sourcesDb.isEmpty()) {
            return fetchSources()
        }

        return sourcesDb
    }

    private suspend fun fetchSources(): List<SourceRaw> {
        val fetchedSources = newsApiService.fetchSources()
        return fetchedSources
    }
}