package com.dalmuina.data.network


import com.dalmuina.data.network.ktor.KtorClientProvider
import com.dalmuina.data.network.ktor.KtorNetworkClient
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