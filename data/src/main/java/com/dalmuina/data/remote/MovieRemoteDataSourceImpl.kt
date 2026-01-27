package com.dalmuina.data.remote


import com.dalmuina.data.dto.MovieDto
import com.dalmuina.data.dto.MoviesResponseDto
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result


class MovieRemoteDataSourceImpl(
    private val api: MovieApiService
) : MovieRemoteDataSource {

    override suspend fun getPopularMovies(): Result<MoviesResponseDto, MovieError> =
        safeApiCall { api.getPopularMovies() }

    override suspend fun getMovie(movieId: Int): Result<MovieDto, MovieError> =
        safeApiCall { api.getMovie(movieId) }
}
