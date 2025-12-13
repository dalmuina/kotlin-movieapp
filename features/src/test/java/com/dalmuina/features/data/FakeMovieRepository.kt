package com.dalmuina.features.data

import com.dalmuina.core.model.Movie
import com.dalmuina.features.movies.domain.MovieRepository

class FakeMovieRepository: MovieRepository {

    var moviesToReturn: List<Movie> = emptyList()
    var movieDetailToReturn : Movie? = null
    var shouldTrow = false

    override suspend fun getPopularMovies(): List<Movie> {
        if (shouldTrow) throw RuntimeException("repository error")
        return moviesToReturn
    }

    override suspend fun getMovieDetail(movieId: Int): Movie {
        if (shouldTrow) throw RuntimeException("repository error")
        return movieDetailToReturn ?: throw IllegalStateException("no movie detail set")
    }
}