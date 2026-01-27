package com.dalmuina.data.network

import io.ktor.util.reflect.typeInfo

suspend inline fun <reified T : Any> NetworkClient.get(endpoint: String): T {
    return executeGet(endpoint, typeInfo<T>())
}