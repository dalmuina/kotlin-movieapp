package com.dalmuina.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseDto(
    val page: Int,
    val results: List<MovieDto>
)
