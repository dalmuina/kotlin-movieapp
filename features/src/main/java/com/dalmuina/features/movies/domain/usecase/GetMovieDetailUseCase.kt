package com.dalmuina.features.movies.domain.usecase

import com.dalmuina.core.model.Movie
import com.dalmuina.features.movies.domain.MovieRepository

class GetMovieDetailUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Movie {
        return repository.getMovieDetail(movieId)
    }
}