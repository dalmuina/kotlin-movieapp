package com.dalmuina.domain

import com.dalmuina.domain.model.Movie
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result


interface MovieRepository {
    suspend fun getPopularMovies(): Result<List<Movie>, MovieError>
    suspend fun getMovieDetail(movieId: Int): Result<Movie, MovieError>
}