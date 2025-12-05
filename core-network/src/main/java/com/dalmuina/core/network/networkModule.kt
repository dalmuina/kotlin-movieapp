package com.dalmuina.core.network

import com.dalmuina.core.network.ktor.KtorClientProvider
import com.dalmuina.core.network.ktor.KtorNetworkClient
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