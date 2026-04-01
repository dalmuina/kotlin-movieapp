package com.dalmuina.domain.usecase

import com.dalmuina.domain.MovieRepository
import com.dalmuina.domain.model.Movie
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetPopularMoviesUseCase(
    private val repository: MovieRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Result<List<Movie>, MovieError> =
        withContext(dispatcher) {
            repository.getPopularMovies()
        }
}