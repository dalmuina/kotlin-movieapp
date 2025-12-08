package com.dalmuina.features.movies.domain.di

import com.dalmuina.features.movies.domain.usecase.GetMovieDetailUseCase
import com.dalmuina.features.movies.domain.usecase.GetPopularMoviesUseCase
import org.koin.dsl.module

val moviesDomainModule = module {
    factory { GetPopularMoviesUseCase(get()) }
    factory { GetMovieDetailUseCase(get()) }
}