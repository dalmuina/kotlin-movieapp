package com.dalmuina

import com.dalmuina.data.dto.MovieDto
import com.dalmuina.data.dto.MoviesResponseDto

object MovieDtoTestData {

    val movieDto = MovieDto(
        id = 1,
        title = "The Matrix",
        posterPath = "/matrix.jpg",
        overview = "A hacker discovers reality is a simulation.",
        rating = 8.7
    )

    val responseDto = MoviesResponseDto(
        page = 1,
        results = listOf(
            movieDto,
            movieDto.copy(id = 2, title = "Interstellar", rating = 9.0),
            movieDto.copy(id = 3, title = "Dune Part II", rating = 8.5)
        )
    )
}