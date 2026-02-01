package com.dalmuina.domain.di

import com.dalmuina.domain.usecase.GetMovieDetailUseCase
import com.dalmuina.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moviesDomainModule = module {

    single<CoroutineDispatcher>(named("IO")) { Dispatchers.IO }


    factory { GetPopularMoviesUseCase(
        repository = get(),
        dispatcher = get(named("IO"))) }
    factory { GetMovieDetailUseCase(
        repository= get(),
        dispatcher = get(named("IO"))) }
}