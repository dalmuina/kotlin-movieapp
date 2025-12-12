package com.dalmuina.features.movies.ui.di

import com.dalmuina.features.movies.ui.movieDetail.MovieDetailViewModel
import com.dalmuina.features.movies.ui.movies.MoviesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moviesUiModule = module {
    viewModel {
        MoviesViewModel(get())
    }
    viewModel {
        MovieDetailViewModel(
            getMovieDetailUseCase = get()
        )
    }
}