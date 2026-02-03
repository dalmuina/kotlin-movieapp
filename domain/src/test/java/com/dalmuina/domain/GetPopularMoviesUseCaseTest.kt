package com.dalmuina.domain

import com.dalmuina.MovieTestData
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result
import com.dalmuina.domain.usecase.GetPopularMoviesUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPopularMoviesUseCaseTest {

    @Test
    fun `returns movies when repository succeeds`() = runTest {
        // Given
        val repository = mockk<MovieRepository>()
        val dispatcher = StandardTestDispatcher(testScheduler)

        coEvery { repository.getPopularMovies() } returns
                Result.Success(MovieTestData.sampleMovies)

        val useCase = GetPopularMoviesUseCase(
            repository = repository,
            dispatcher = dispatcher
        )

        // When
        val result = useCase()

        testScheduler.advanceUntilIdle()

        // Then
        result shouldBe Result.Success(MovieTestData.sampleMovies)
        coVerify(exactly = 1) { repository.getPopularMovies() }
    }

    @Test
    fun `returns error when repository fails`() = runTest {
        // Given
        val repository = mockk<MovieRepository>()
        val dispatcher = StandardTestDispatcher(testScheduler)

        coEvery { repository.getPopularMovies() } returns
                Result.Error(MovieError.Network)

        val useCase = GetPopularMoviesUseCase(
            repository = repository,
            dispatcher = dispatcher
        )

        // When
        val result = useCase()

        testScheduler.advanceUntilIdle()

        // Then
        result shouldBe Result.Error(MovieError.Network)
        coVerify(exactly = 1) { repository.getPopularMovies() }
    }

}