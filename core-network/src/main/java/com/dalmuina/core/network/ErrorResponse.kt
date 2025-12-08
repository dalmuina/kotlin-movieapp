package com.dalmuina.core.network

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val statusMessage: String? = null,
    val statusCode: Int? = null
)