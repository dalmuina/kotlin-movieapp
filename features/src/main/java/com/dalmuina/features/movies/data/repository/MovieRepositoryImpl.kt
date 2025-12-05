package com.dalmuina.features.movies.data.repository

import com.dalmuina.core.model.Movie
import com.dalmuina.features.movies.data.mapper.toDomain
import com.dalmuina.features.movies.data.remote.MovieApiService
import com.dalmuina.features.movies.domain.MovieRepository

class MovieRepositoryImpl(
    private val api: MovieApiService
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return api.getPopularMovies()
            .results
            .map { it.toDomain() }
    }
}