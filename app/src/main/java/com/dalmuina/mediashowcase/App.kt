package com.dalmuina.mediashowcase

import android.app.Application
import com.dalmuina.di.moviesDataModule
import com.dalmuina.di.moviesDomainModule
import com.dalmuina.di.moviesUiModule
import com.dalmuina.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
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
