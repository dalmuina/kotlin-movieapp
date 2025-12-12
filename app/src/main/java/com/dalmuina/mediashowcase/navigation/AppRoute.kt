package com.dalmuina.mediashowcase.navigation

sealed interface AppRoute {
    val route: String

    data object Movies : AppRoute {
        override val route = "movies"
    }

    data class MovieDetail(val movieId: Int): AppRoute{
        override val route = "movie_detail/$movieId"

        companion object {
            const val BASE = "movie_detail"
            const val ARG_MOVIE_ID = "movieId"
            val pattern = "$BASE/{$ARG_MOVIE_ID}"
        }
    }
}