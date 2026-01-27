package com.dalmuina.domain.di

import com.dalmuina.domain.usecase.GetMovieDetailUseCase
import com.dalmuina.domain.usecase.GetPopularMoviesUseCase
import org.koin.dsl.module

val moviesDomainModule = module {
    factory { GetPopularMoviesUseCase(get()) }
    factory { GetMovieDetailUseCase(get()) }
}