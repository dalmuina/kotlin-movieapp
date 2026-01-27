package com.dalmuina.data.remote

import com.dalmuina.data.dto.MovieDto
import com.dalmuina.data.dto.MoviesResponseDto
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(): Result<MoviesResponseDto, MovieError>
    suspend fun getMovie(movieId: Int): Result<MovieDto, MovieError>
}