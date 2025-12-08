package com.dalmuina.features.movies.ui.model

import androidx.compose.runtime.Immutable
import com.dalmuina.core.model.Movie

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

private fun buildPosterUrl(path: String): String =
    "https://image.tmdb.org/t/p/w500$path"

