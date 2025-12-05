package com.dalmuina.core.network

import org.koin.dsl.module

val networkModule = module {
    single { NetworkConfig() }

    single { KtorClientProvider(get()).createClient() }

    single<NetworkClient> {
        KtorNetworkClient(
            client = get(),
            config = get()
        )
    }
}