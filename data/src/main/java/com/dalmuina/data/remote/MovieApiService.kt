package com.dalmuina.data.remote

import com.dalmuina.data.dto.MovieDto
import com.dalmuina.data.dto.MoviesResponseDto
import com.dalmuina.data.network.NetworkClient
import com.dalmuina.data.network.get


class MovieApiService(
    private val client: NetworkClient
) {
    suspend fun getPopularMovies(): MoviesResponseDto {
        return client.get(Endpoints.POPULAR_MOVIES)
    }

    suspend fun getMovie(movieId: Int): MovieDto {
        return client.get(Endpoints.movieDetail(movieId))
    }
}