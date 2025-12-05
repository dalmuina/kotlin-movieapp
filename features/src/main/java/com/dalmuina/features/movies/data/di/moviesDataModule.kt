package com.dalmuina.features.movies.data.di

import com.dalmuina.features.movies.data.remote.MovieApiService
import com.dalmuina.features.movies.data.repository.MovieRepositoryImpl
import org.koin.dsl.module

val moviesDataModule = module {

    single { MovieApiService(get(), get()) }

    single<MovieRepository> { MovieRepositoryImpl(get()) }
}