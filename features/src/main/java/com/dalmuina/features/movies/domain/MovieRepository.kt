package com.dalmuina.features.movies.domain

import com.dalmuina.core.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): List<Movie>
}