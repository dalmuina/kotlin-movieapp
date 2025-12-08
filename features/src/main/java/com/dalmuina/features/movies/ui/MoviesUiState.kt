package com.dalmuina.features.movies.ui

import androidx.compose.runtime.Immutable
import com.dalmuina.features.movies.ui.model.MovieUi

@Immutable
data class MoviesUiState(
    val isLoading: Boolean = false,
    val movies: List<MovieUi> = emptyList(),
    val error: String? = null
)