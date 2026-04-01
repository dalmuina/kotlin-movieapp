package com.dalmuina.domain.testdata

import com.dalmuina.domain.model.Movie

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

object MovieTestData {

    val sampleMovie = Movie(
        id = 1,
        title = "The Matrix",
        posterUrl = "$IMAGE_BASE_URL/matrix.jpg",
        overview = "A hacker discovers reality is a simulation.",
        rating = 8.7
    )

    val sampleMovies = listOf(
        sampleMovie,
        sampleMovie.copy(id = 2, title = "Interstellar", rating = 9.0),
        sampleMovie.copy(id = 3, title = "Dune Part II", rating = 8.5)
    )
}