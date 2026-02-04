package com.dalmuina.features.movies.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalmuina.domain.model.onError
import com.dalmuina.domain.model.onSuccess
import com.dalmuina.domain.usecase.GetMovieDetailUseCase
import com.dalmuina.features.movies.model.MovieUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> = _uiState


    fun process(intent: MovieDetailIntent) {
        when (intent) {
            is MovieDetailIntent.LoadMovie -> loadMovie(intent.movieId)
        }
    }

    private fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            reduce {
                copy(isLoading = true)
            }

            getMovieDetailUseCase(movieId)
                .onSuccess { movie ->
                    reduce {
                        copy(
                            isLoading = false,
                            movie = MovieUi.fromDomain(movie),
                            error = null
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

    private inline fun reduce(
        reducer: MovieDetailUiState.() -> MovieDetailUiState
    ) {
        _uiState.update {
            it.reducer()
        }
    }
}