package com.dalmuina.core.network.ktor

import com.dalmuina.core.network.NetworkClient
import com.dalmuina.core.network.NetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.util.reflect.TypeInfo

class KtorNetworkClient(
    private val client: HttpClient,
    private val config: NetworkConfig
) : NetworkClient {

    override suspend fun <T : Any> executeGet(
        endpoint: String,
        typeInfo: TypeInfo
    ): T {
        val url = "${config.baseUrl}$endpoint"
        println(">>> Calling URL: $url")

        val response = client.get(url)

        val raw = response.bodyAsText()
        println(">>> RAW RESPONSE: $raw")

        return response.body(typeInfo)
    }

}