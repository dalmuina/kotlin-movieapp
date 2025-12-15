package com.dalmuina.features.ui.movieDetail

import app.cash.turbine.test
import com.dalmuina.MainDispatcherRule
import com.dalmuina.core.model.Movie
import com.dalmuina.features.data.FakeMovieRepository
import com.dalmuina.features.movies.domain.usecase.GetMovieDetailUseCase
import com.dalmuina.features.movies.ui.movieDetail.MovieDetailViewModel
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieDetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private lateinit var repository: FakeMovieRepository
    private lateinit var useCase: GetMovieDetailUseCase
    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        repository = FakeMovieRepository()
        useCase = GetMovieDetailUseCase(repository)
        viewModel = MovieDetailViewModel(useCase, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `Initial state is idle and contains no movie`() = runTest {
        val state = viewModel.uiState.value

        state.isLoading shouldBe false
        state.movie shouldBe null
        state.error shouldBe null
    }

    @Test
    fun `WHEN loadMovie is called THEN loading state is emmited`() = runTest {
        viewModel.uiState.test{
            awaitItem().isLoading shouldBe false
            viewModel.loadMovie(1)
            awaitItem().isLoading shouldBe true
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `WHEN loadMovie succeeds THEN uiState contains mapped MovieUi`() = runTest {
        repository.movieDetailToReturn = Movie(
            id = 10,
            title = "The Dark Knight",
            posterUrl = null,
            overview = "desc",
            rating = 9.0
        )
        viewModel.loadMovie(10)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        state.isLoading shouldBe false
        state.movie shouldNotBe null

        with (state.movie!!){
            id shouldBe 10
            title shouldBe "The Dark Knight"
            rating shouldBe 9.0
        }
    }

    @Test
    fun `WHEN repository throws THEN error state is emitted`() = runTest {
        repository.shouldTrow = true
        viewModel.loadMovie(99)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        state.isLoading shouldBe false
        state.movie shouldBe null
        state.error shouldNotBe null
        state.error shouldBe "repository error"
    }

    @Test
    fun `WHEN repository returns null detail THEN IllegalStateException is caught`() = runTest {
        repository.movieDetailToReturn = null

        viewModel.loadMovie(99)
        advanceUntilIdle()

        val state = viewModel.uiState.value
        state.movie shouldBe null
        state.error shouldNotBe null
    }
}