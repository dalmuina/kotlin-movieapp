package com.dalmuina.domain

import com.dalmuina.MovieTestData
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result
import com.dalmuina.domain.usecase.GetMovieDetailUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetMovieDetailUseCaseTest {

    @Test
    fun `returns movie when repository succeeds`() = runTest {
        // Given
        val repository = mockk<MovieRepository>()
        val dispatcher = StandardTestDispatcher(testScheduler)

        coEvery { repository.getMovieDetail(1) } returns
                Result.Success(MovieTestData.sampleMovie)

        val useCase = GetMovieDetailUseCase(
            repository = repository,
            dispatcher = dispatcher
        )

        // When
        val result = useCase(1)

        testScheduler.advanceUntilIdle()

        // Then
        result shouldBe Result.Success(MovieTestData.sampleMovie)
        coVerify(exactly = 1) { repository.getMovieDetail(1) }
    }

    @Test
    fun `returns error when repository fails`() = runTest {
        // Given
        val repository = mockk<MovieRepository>()
        val dispatcher = StandardTestDispatcher(testScheduler)

        coEvery { repository.getMovieDetail(1) } returns
                Result.Error(MovieError.Network)

        val useCase = GetMovieDetailUseCase(
            repository = repository,
            dispatcher = dispatcher
        )

        // When
        val result = useCase(1)

        testScheduler.advanceUntilIdle()

        // Then
        result shouldBe Result.Error(MovieError.Network)
        coVerify(exactly = 1) { repository.getMovieDetail(1) }
    }

}