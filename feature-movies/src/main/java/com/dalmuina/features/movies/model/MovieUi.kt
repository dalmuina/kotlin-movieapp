package com.dalmuina.features.movies.model

import androidx.compose.runtime.Immutable
import com.dalmuina.domain.model.Movie

@Immutable
data class MovieUi(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val rating: Double
) {
    companion object {
        fun fromDomain(movie: Movie): MovieUi {
            return MovieUi(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                posterUrl = movie.posterUrl ?: "",   // evita null en Compose
                rating = movie.rating
            )
        }
    }
}