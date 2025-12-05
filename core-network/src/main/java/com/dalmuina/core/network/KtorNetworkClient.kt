package com.dalmuina.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.reflect.TypeInfo

class KtorNetworkClient(
    private val client: HttpClient,
    private val config: NetworkConfig
) : NetworkClient {

    override suspend fun <T : Any> executeGet(
        endpoint: String,
        typeInfo: TypeInfo
    ): T {
        val response = client.get("${config.baseUrl}$endpoint")
        return response.body(typeInfo)
    }
}