package com.dalmuina.domain.usecase

import com.dalmuina.domain.MovieRepository
import com.dalmuina.domain.model.Movie
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetMovieDetailUseCase(
    private val repository: MovieRepository,
    private val dispatcher: CoroutineDispatcher

) {
    suspend operator fun invoke(movieId: Int): Result<Movie, MovieError> =
        withContext(dispatcher) {
            repository.getMovieDetail(movieId)
        }
}