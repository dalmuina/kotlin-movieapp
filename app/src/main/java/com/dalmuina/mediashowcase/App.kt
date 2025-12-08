package com.dalmuina.mediashowcase

import android.app.Application
import com.dalmuina.core.network.networkModule
import com.dalmuina.features.movies.data.di.moviesDataModule
import com.dalmuina.features.movies.domain.di.moviesDomainModule
import com.dalmuina.features.movies.ui.di.moviesUiModule
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