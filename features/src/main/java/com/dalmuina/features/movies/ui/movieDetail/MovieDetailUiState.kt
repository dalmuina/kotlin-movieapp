package com.dalmuina.features.movies.ui.movieDetail

import com.dalmuina.features.movies.ui.model.MovieUi

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movie: MovieUi?= null,
    val error: String? = null
)