package com.dalmuina.core.model

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val overview: String,
    val rating: Double
)
