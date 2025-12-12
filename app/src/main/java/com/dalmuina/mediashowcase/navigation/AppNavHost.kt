package com.dalmuina.mediashowcase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier,
) {
    val navController: NavHostController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppRoute.Movies.route
    ) {
        composable(AppRoute.Movies.route) {
            MoviesRoute(onMovieSelected = { movieId ->
                navController.navigate(AppRoute.MovieDetail(movieId).route)
            })
        }
        composable(
            route = AppRoute.MovieDetail.pattern,
            arguments = listOf(navArgument(AppRoute.MovieDetail.ARG_MOVIE_ID) { type = NavType.IntType })
        ) { entry ->
            val movieId = entry.arguments?.getInt(AppRoute.MovieDetail.ARG_MOVIE_ID) ?: return@composable
            MovieDetailRoute(movieId = movieId, onBack = { navController.popBackStack() })
        }
    }
}