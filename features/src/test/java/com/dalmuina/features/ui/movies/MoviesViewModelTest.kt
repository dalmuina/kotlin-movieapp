package com.dalmuina.features.ui.movies

import app.cash.turbine.test
import com.dalmuina.MainDispatcherRule
import com.dalmuina.core.model.Movie
import com.dalmuina.features.data.FakeMovieRepository
import com.dalmuina.features.movies.domain.usecase.GetPopularMoviesUseCase
import com.dalmuina.features.movies.ui.movies.MoviesViewModel
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private lateinit var repository: FakeMovieRepository
    private lateinit var useCase: GetPopularMoviesUseCase
    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        repository = FakeMovieRepository()
        useCase = GetPopularMoviesUseCase(repository)
        viewModel = MoviesViewModel(useCase, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `Initial state emits loading as first emission`() = runTest {
        viewModel.uiState.test {
            // primer valor: default
            awaitItem().isLoading shouldBe false

            viewModel.loadMovies()
            advanceUntilIdle()

            // segundo valor: loading
            awaitItem().isLoading shouldBe true

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `WHEN loadMovies succeeds THEN state contains movies`() = runTest {
        //WHEN
        repository.moviesToReturn = listOf(
            Movie(1, "Inception", null, "A mind-bending thriller", 8.8),
            Movie(2, "Interstellar", null, "Space exploration", 9.0)
        )
        //THEN
        viewModel.loadMovies()
        advanceUntilIdle()
        //ASSERT
        val state = viewModel.uiState.value
        state.isLoading shouldBe false
        state.movies shouldHaveSize 2
        state.movies.first().title shouldBe "Inception"
    }

    @Test
    fun `WHEN loadMovies returns empty list THEN state contains empty movies`() = runTest {
        repository.moviesToReturn = emptyList()

        viewModel.loadMovies()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        state.movies.size shouldBe 0
    }

    @Test
    fun `Movies are mapped to MovieUi correctly`() = runTest {
        repository.moviesToReturn = listOf(
            Movie(10, "Batman Begins", null, "desc", 8.3)
        )

        viewModel.loadMovies()
        advanceUntilIdle()

        val movieUi = viewModel.uiState.value.movies.first()

        movieUi.id shouldBe 10
        movieUi.title shouldBe "Batman Begins"
        movieUi.rating shouldBe 8.3
    }
}