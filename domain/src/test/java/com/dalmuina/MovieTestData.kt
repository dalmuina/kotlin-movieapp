package com.dalmuina

import com.dalmuina.domain.model.Movie

object MovieTestData {
    val sampleMovie = Movie(
        id = 1,
        title = "The Matrix",
        overview = "A hacker discovers reality is a simulation and joins the resistance.",
        posterUrl = "",
        rating = 8.7
    )

    val sampleMovies = listOf(
        sampleMovie,
        sampleMovie.copy(id = 2, title = "Interstellar", rating = 9.0),
        sampleMovie.copy(id = 3, title = "Dune Part II", rating = 8.5)
    )
}