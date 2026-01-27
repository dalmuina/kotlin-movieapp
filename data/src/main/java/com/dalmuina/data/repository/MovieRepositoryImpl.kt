package com.dalmuina.data.repository

import com.dalmuina.data.mapper.toDomain
import com.dalmuina.data.remote.MovieRemoteDataSource
import com.dalmuina.domain.MovieRepository
import com.dalmuina.domain.model.AppError
import com.dalmuina.domain.model.Movie
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result
import com.dalmuina.domain.model.map

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override suspend fun getPopularMovies(): Result<List<Movie>, MovieError> {
        return remoteDataSource.getPopularMovies()
            .map { dto ->
                dto.results.map{
                    it.toDomain()
                }
            }

    }

    override suspend fun getMovieDetail(movieId: Int): Result<Movie, MovieError> {
        return remoteDataSource.getMovie(movieId)
            .map { it.toDomain() }
    }
}
