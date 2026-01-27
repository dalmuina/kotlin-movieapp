package com.dalmuina.data.network

data class NetworkConfig(
    val baseUrl: String = "https://api.themoviedb.org/3",
    val timeoutMillis: Long = 30_000,
)