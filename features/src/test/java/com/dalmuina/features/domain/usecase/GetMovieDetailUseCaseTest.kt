package com.dalmuina.features.domain.usecase

import com.dalmuina.core.model.Movie
import com.dalmuina.features.data.FakeMovieRepository
import com.dalmuina.features.movies.domain.usecase.GetMovieDetailUseCase
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetMovieDetailUseCaseTest {
    private val fakeRepository = FakeMovieRepository()
    private val useCase = GetMovieDetailUseCase(fakeRepository)

    @Test
    fun `WHEN repository returns detail THEN use case returns it`() = runTest {
        //WHEN
        val avatar = Movie(10, "Avatar", null, "Blue guys", 7.5)
        fakeRepository.movieDetailToReturn = avatar
        //THEN
        val result = useCase(10)
        //ASSERT
        result.title shouldBe "Avatar"
        result.id shouldBe 10
    }

    @Test(expected = RuntimeException::class)
    fun `WHEN repository throws THEN use case throws`() = runTest {
        //WHEN
        fakeRepository.shouldTrow = true
        //THEN
        useCase(10)
    }
}