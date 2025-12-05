package com.dalmuina.features.movies.domain.usecase

import com.dalmuina.core.model.Movie
import com.dalmuina.features.movies.domain.MovieRepository

class GetPopularMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): List<Movie> {
        return repository.getPopularMovies()
    }
}