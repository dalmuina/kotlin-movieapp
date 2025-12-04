package com.dalmuina.core.network

import com.dalmuina.core.model.MovieListResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TmdbService(
    private val client: HttpClient,
    private val apiKey: String
) {
    suspend fun getPopularMovies(): MovieListResponse =
        client.get("https://api.themoviedb.org/3/movie/popular") {
            parameter("api_key", apiKey)
        }.body()
}