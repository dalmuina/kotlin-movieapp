package com.dalmuina.domain.usecase

import com.dalmuina.domain.MovieRepository
import com.dalmuina.domain.model.Movie
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result

class GetMovieDetailUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Movie, MovieError> {
        return repository.getMovieDetail(movieId)
    }
}