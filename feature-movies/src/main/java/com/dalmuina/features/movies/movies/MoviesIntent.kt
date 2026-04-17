package com.dalmuina.features.movies.movies

sealed interface MoviesIntent {
    data object LoadMovies: MoviesIntent
}