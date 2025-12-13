package com.dalmuina.features.domain.usecase

import com.dalmuina.core.model.Movie
import com.dalmuina.features.data.FakeMovieRepository
import com.dalmuina.features.movies.domain.usecase.GetPopularMoviesUseCase
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPopularMoviesUseCaseTest {
    private val fakeRepository = FakeMovieRepository()
    private val useCase = GetPopularMoviesUseCase(fakeRepository)

    @Test
    fun `WHEN repository returns movies THEN use case returns the same list`() = runTest {
        //WHEN
        fakeRepository.moviesToReturn = listOf(
            Movie(1, "Inception", null, "Desc", 8.8),
            Movie(id = 2, "Interstellar", null, "Desc", 9.0)
        )
        //THEN
        val result = useCase()
        //ASSERT
        result.shouldHaveSize(2)
        result.first().title shouldBe "Inception"
    }

    @Test
    fun `WHEN repository returns empty THEN use case returns empty`() = runTest {
        //WHEN
        fakeRepository.moviesToReturn = emptyList()
        //THEN
        val result = useCase()
        //ASSERT
        result.shouldHaveSize(0)
    }

    @Test(expected = RuntimeException::class)
    fun `WHEN repository throws THEN use case throws`() = runTest{
        //WHEN
        fakeRepository.shouldTrow = true
        //THEN
        useCase()
    }
}