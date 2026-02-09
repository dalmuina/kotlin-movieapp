package com.dalmuina.features.movies.movies

import app.cash.turbine.test
import com.dalmuina.MainDispatcherRule
import com.dalmuina.domain.MovieRepository
import com.dalmuina.domain.testdata.MovieTestData
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result
import com.dalmuina.domain.usecase.GetPopularMoviesUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when LoadMovies then emits loading and success state`() = runTest {
        //Given
        val testDispatcher = StandardTestDispatcher(testScheduler)

        val repository = mockk<MovieRepository>()

        coEvery { repository.getPopularMovies() } returns
                Result.Success(MovieTestData.sampleMovies)

        val useCase = GetPopularMoviesUseCase(
            repository = repository,
            dispatcher = testDispatcher
        )

        val viewModel = MoviesViewModel(useCase)

        viewModel.uiState.test {

            // initial
            awaitItem() shouldBe MoviesUiState()

            // when
            viewModel.process(MoviesIntent.LoadMovies)

            // loading
            awaitItem().isLoading shouldBe true

            testScheduler.advanceUntilIdle()

            // success
            val success = awaitItem()
            success.isLoading shouldBe false
            success.movies.size shouldBe 3
            success.movies.first().title shouldBe "The Matrix"
            success.error shouldBe null

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when LoadMovies fails then emits loading and error state`() = runTest {
        // Given
        val testDispatcher = StandardTestDispatcher(testScheduler)

        val repository = mockk<MovieRepository>()

        coEvery { repository.getPopularMovies() } returns
                Result.Error(MovieError.Network)

        val useCase = GetPopularMoviesUseCase(
            repository = repository,
            dispatcher = testDispatcher
        )

        val viewModel = MoviesViewModel(useCase)

        viewModel.uiState.test {

            // initial
            awaitItem() shouldBe MoviesUiState()

            // when
            viewModel.process(MoviesIntent.LoadMovies)

            // loading
            awaitItem().isLoading shouldBe true

            testScheduler.advanceUntilIdle()

            // error
            val errorState = awaitItem()
            errorState.isLoading shouldBe false
            errorState.movies shouldBe emptyList()
            errorState.error shouldBe MovieError.Network.toString()

            cancelAndIgnoreRemainingEvents()
        }
    }

}