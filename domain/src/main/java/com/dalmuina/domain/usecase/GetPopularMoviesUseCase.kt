package com.dalmuina.domain.usecase

import com.dalmuina.domain.MovieRepository
import com.dalmuina.domain.model.Movie
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result

class GetPopularMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): Result<List<Movie>, MovieError> {
        return repository.getPopularMovies()
    }
}