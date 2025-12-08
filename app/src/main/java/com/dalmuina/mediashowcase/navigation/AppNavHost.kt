package com.dalmuina.mediashowcase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dalmuina.features.movies.ui.movieDetail.MovieDetailRoute
import com.dalmuina.features.movies.ui.movies.MoviesRoute

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "movies"
    ) {
        composable("movies") {
            MoviesRoute(onMovieSelected = { movieId ->
                navController.navigate("movie_detail/$movieId")
            })
        }
        composable(
            route = "movie_detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            MovieDetailRoute()
        }
    }
}