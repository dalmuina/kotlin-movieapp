package com.dalmuina.features.movies.ui.preview

import com.dalmuina.features.movies.ui.model.MovieUi

object MoviePreviewData {
    val sampleMovie = MovieUi(
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