package com.dalmuina.di

import com.dalmuina.features.movies.movieDetail.MovieDetailViewModel
import com.dalmuina.features.movies.movies.MoviesViewModel
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