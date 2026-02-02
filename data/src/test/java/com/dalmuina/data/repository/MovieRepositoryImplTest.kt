package com.dalmuina.data.repository

import com.dalmuina.MovieDtoTestData
import com.dalmuina.MovieTestData
import com.dalmuina.data.remote.MovieRemoteDataSource
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MovieRepositoryImplTest {

    @Test
    fun `getPopularMovies maps dto to domain`() = runTest {
        // Given
        val remoteDataSource = mockk<MovieRemoteDataSource>()

        coEvery { remoteDataSource.getPopularMovies() } returns
                Result.Success(MovieDtoTestData.responseDto)

        val repository = MovieRepositoryImpl(remoteDataSource)

        // When
        val result = repository.getPopularMovies()

        // Then
        result shouldBe Result.Success(MovieTestData.sampleMovies)

        coVerify(exactly = 1) { remoteDataSource.getPopularMovies() }
    }

    @Test
    fun `getPopularMovies propagates error from data source`() = runTest {
        // Given
        val remoteDataSource = mockk<MovieRemoteDataSource>()

        coEvery { remoteDataSource.getPopularMovies() } returns
                Result.Error(MovieError.Network)

        val repository = MovieRepositoryImpl(remoteDataSource)

        // When
        val result = repository.getPopularMovies()

        // Then
        result shouldBe Result.Error(MovieError.Network)

        coVerify(exactly = 1) { remoteDataSource.getPopularMovies() }
    }

    @Test
    fun `getMovieDetail maps dto to domain`() = runTest {
        val remoteDataSource = mockk<MovieRemoteDataSource>()

        coEvery { remoteDataSource.getMovie(1) } returns
                Result.Success(MovieDtoTestData.movieDto)

        val repository = MovieRepositoryImpl(remoteDataSource)

        val result = repository.getMovieDetail(1)

        result shouldBe Result.Success(MovieTestData.sampleMovie)

        coVerify { remoteDataSource.getMovie(1) }
    }

}