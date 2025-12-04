package com.dalmuina.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

abstract class ApiService(
    protected val client: HttpClient,
    protected val config: NetworkConfig,
) {

    protected suspend inline fun <reified T> get(
        endpoint: String,
        noinline block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return client.get("${config.baseUrl}$endpoint") {
            block()
        }.body<T>()
    }

    protected suspend inline fun <reified T, reified B> post(
        endpoint: String,
        body: B,
        noinline block: HttpRequestBuilder.() -> Unit = {}
    ): T {
        return client.post("${config.baseUrl}$endpoint") {
            setBody(body)
            block()
        }.body<T>()
    }
}