package com.petros.efthumiou.dailypulse.sources.data.db

import com.petros.efthumiou.dailypulse.sources.data.SourceRaw
import petros.efthymiou.dailypulse.db.DailyPulseDatabase

class SourcesDataSource(private val database: DailyPulseDatabase) {

    fun getAllSources(): List<SourceRaw> =
        database.dailyPulseDatabaseQueries.selectAllSources<SourceRaw>(::mapToSourceRaw).executeAsList()

    fun insertSources(sources: List<SourceRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            sources.forEach { sourceRaw ->
                insertSource(sourceRaw)
            }
        }
    }

    fun clearSources() =
        database.dailyPulseDatabaseQueries.removeAllSources()

    private fun insertSource(sourceRaw: SourceRaw) {
        database.dailyPulseDatabaseQueries.insertSource(
            sourceRaw.id,
            sourceRaw.name,
            sourceRaw.description,
            sourceRaw.url,
            sourceRaw.category
        )
    }

    private fun mapToSourceRaw(
        id: String,
        name: String,
        description: String?,
        url: String?,
        category: String?
    ): SourceRaw = SourceRaw(
        id = id,
        name = name,
        description = description ?: "",
        url = url ?: "",
        category = category ?: "",
        language = "",
        country = ""
    )
}