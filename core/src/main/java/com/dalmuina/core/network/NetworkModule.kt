package com.dalmuina.core.network

import org.koin.dsl.module

val networkModule = module {
    single { KtorClientProvider.create() }

    single {
        TmdbService(
            client = get(),
            apiKey = BuildConfig.TMDB_API_KEY
        )
    }
}