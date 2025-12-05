package com.dalmuina.features.movies.data.di

import com.dalmuina.features.movies.data.remote.MovieApiService
import com.dalmuina.features.movies.data.repository.MovieRepositoryImpl
import com.dalmuina.features.movies.domain.MovieRepository
import org.koin.dsl.module

val moviesDataModule = module {

    single { MovieApiService(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get()) }
}