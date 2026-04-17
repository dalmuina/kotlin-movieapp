package com.dalmuina.features.movies.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalmuina.domain.model.onError
import com.dalmuina.domain.model.onSuccess
import com.dalmuina.domain.usecase.GetPopularMoviesUseCase
import com.dalmuina.features.movies.model.MovieUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MoviesUiState())
    val uiState: StateFlow<MoviesUiState> = _uiState

    fun process(intent: MoviesIntent) {
        when (intent) {
            is MoviesIntent.LoadMovies -> loadMovies()
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            reduce { copy(isLoading = true, error = null) }
            getPopularMoviesUseCase()
                .onSuccess { movies ->
                    reduce {
                        copy(
                            isLoading = false,
                            movies = movies.map { movie -> MovieUi.fromDomain(movie) }
                        )
                    }
                }
                .onError { error ->
                    reduce {
                        copy(
                            isLoading = false,
                            error = error.toString()
                        )
                    }
                }

        }
    }

    private inline fun reduce(reducer: MoviesUiState.() -> MoviesUiState) {
        _uiState.update { it.reducer() }
    }
}