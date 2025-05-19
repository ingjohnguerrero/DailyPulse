package com.petros.efthumiou.dailypulse.sources.presentation
import com.petros.efthumiou.dailypulse.sources.application.Source
import com.petros.efthumiou.dailypulse.sources.data.SourceRaw

data class SourcesState(
    val sources: List<Source> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)