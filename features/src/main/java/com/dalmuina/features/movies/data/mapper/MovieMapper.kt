package com.dalmuina.features.movies.data.mapper

import com.dalmuina.core.model.Movie
import com.dalmuina.features.movies.data.dto.MovieDto

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterUrl = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" },
        overview = overview,
        rating = rating
    )
}
