package com.petros.efthumiou.dailypulse.sources.application

data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String,
    val locale: String,
) {}
