package com.dalmuina.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(
    @SerialName("results") val results: List<MovieDto>
)

@Serializable
class MovieDto(
    val id: Int,
    val title: String,
    @SerialName("poster_path") val posterPath: String?
)