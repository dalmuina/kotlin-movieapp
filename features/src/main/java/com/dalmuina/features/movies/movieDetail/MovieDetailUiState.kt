package com.dalmuina.features.movies.movieDetail

import com.dalmuina.features.movies.model.MovieUi

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movie: MovieUi?= null,
    val error: String? = null
)