package com.dalmuina.data.mapper

import com.dalmuina.data.dto.MovieDto
import com.dalmuina.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterUrl = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" },
        overview = overview,
        rating = rating
    )
}
