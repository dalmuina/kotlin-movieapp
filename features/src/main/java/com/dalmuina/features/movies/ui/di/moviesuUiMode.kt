package com.dalmuina.features.movies.ui.di

import com.dalmuina.features.movies.ui.MoviesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moviesUiMode = module {
    viewModel { MoviesViewModel(get()) }
}