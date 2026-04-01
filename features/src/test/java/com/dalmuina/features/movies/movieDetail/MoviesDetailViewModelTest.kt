package com.dalmuina.features.movies.movieDetail

import app.cash.turbine.test
import com.dalmuina.MainDispatcherRule
import com.dalmuina.domain.MovieRepository
import com.dalmuina.domain.testdata.MovieTestData
import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result
import com.dalmuina.domain.usecase.GetMovieDetailUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MoviesDetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when LoadMovie then emits loading and success state`() = runTest {
        //Given
        val testDispatcher = StandardTestDispatcher(testScheduler)

        val repository = mockk<MovieRepository>()

        coEvery { repository.getMovieDetail(1) } returns
                Result.Success(MovieTestData.sampleMovie)

        val useCase = GetMovieDetailUseCase(
            repository = repository,
            dispatcher = testDispatcher
        )

        val viewModel = MovieDetailViewModel(useCase)

        viewModel.uiState.test {

            // initial
            awaitItem() shouldBe MovieDetailUiState()

            // when
            viewModel.process(MovieDetailIntent.LoadMovie(1))

            // loading
            awaitItem().isLoading shouldBe true

            testScheduler.advanceUntilIdle()

            // success
            val success = awaitItem()
            success.isLoading shouldBe false
            success.movie?.title shouldBe "The Matrix"
            success.movie?.id shouldBe 1
            success.error shouldBe null

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `when LoadMovie fails then emits loading and error state`() = runTest {
        // Given
        val testDispatcher = StandardTestDispatcher(testScheduler)

        val repository = mockk<MovieRepository>()

        coEvery { repository.getMovieDetail(1) } returns
                Result.Error(MovieError.Network)

        val useCase = GetMovieDetailUseCase(
            repository = repository,
            dispatcher = testDispatcher
        )

        val viewModel = MovieDetailViewModel(useCase)

        viewModel.uiState.test {

            // initial
            awaitItem() shouldBe MovieDetailUiState()

            // when
            viewModel.process(MovieDetailIntent.LoadMovie(1))

            // loading
            awaitItem().isLoading shouldBe true

            testScheduler.advanceUntilIdle()

            // error
            val errorState = awaitItem()
            errorState.isLoading shouldBe false
            errorState.error shouldBe MovieError.Network.toString()

            cancelAndIgnoreRemainingEvents()
        }
    }
}