package com.dalmuina.mediashowcase

import android.app.Application
import com.dalmuina.data.di.moviesDataModule
import com.dalmuina.data.network.networkModule
import com.dalmuina.domain.di.moviesDomainModule

import com.dalmuina.features.movies.di.moviesUiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                moviesDataModule,
                moviesDomainModule,
                moviesUiModule
            )
        }
    }
}
