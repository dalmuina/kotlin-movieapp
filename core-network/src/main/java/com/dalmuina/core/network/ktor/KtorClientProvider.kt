package com.dalmuina.core.network.ktor

import com.dalmuina.core.network.BuildConfig
import com.dalmuina.core.network.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class KtorClientProvider(
    private val config: NetworkConfig
) {

    fun createClient(): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                preconfigured = OkHttpClient.Builder()
                    .connectTimeout(config.timeoutMillis, TimeUnit.MILLISECONDS)
                    .readTimeout(config.timeoutMillis, TimeUnit.MILLISECONDS)
                    .writeTimeout(config.timeoutMillis, TimeUnit.MILLISECONDS)
                    .addInterceptor(apiKeyInterceptor())
                    .build()
            }

            install(Logging) {
                level = LogLevel.BODY
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        prettyPrint = false
                    }
                )
            }
        }
    }

    private fun apiKeyInterceptor(): Interceptor {
        val apiKey = BuildConfig.TMDB_API_KEY

        return Interceptor { chain ->
            val original = chain.request()
            val originalUrl = original.url

            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

            val request = original.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(request)
        }
    }
}