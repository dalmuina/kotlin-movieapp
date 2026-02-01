package com.dalmuina.features.movies.movieDetail

sealed interface MovieDetailIntent {
    data class LoadMovie(val movieId: Int): MovieDetailIntent
}