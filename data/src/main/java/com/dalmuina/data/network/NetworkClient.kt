package com.dalmuina.data.network

import io.ktor.util.reflect.TypeInfo

interface NetworkClient {
    suspend fun <T : Any> executeGet(endpoint: String, typeInfo: TypeInfo): T
}