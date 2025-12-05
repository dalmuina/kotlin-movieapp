package com.dalmuina.core.network

data class NetworkConfig(
    val baseUrl: String = "https://api.themoviedb.org/3",
    val timeoutMillis: Long = 30_000,
)