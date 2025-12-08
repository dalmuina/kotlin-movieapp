package com.dalmuina.features.movies.data.remote

import com.dalmuina.core.network.NetworkClient
import com.dalmuina.core.network.get
import com.dalmuina.features.movies.data.dto.MovieDto
import com.dalmuina.features.movies.data.dto.MoviesResponseDto

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