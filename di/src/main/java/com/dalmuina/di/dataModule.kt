package com.dalmuina.di


import com.dalmuina.data.remote.MovieApiService
import com.dalmuina.data.remote.MovieRemoteDataSource
import com.dalmuina.data.remote.MovieRemoteDataSourceImpl
import com.dalmuina.data.repository.MovieRepositoryImpl
import com.dalmuina.domain.MovieRepository
import org.koin.dsl.module

val moviesDataModule = module {

    single { MovieApiService(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get()) }

    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
}