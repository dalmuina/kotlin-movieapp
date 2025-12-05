package com.dalmuina.core.network

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val status_message: String? = null,
    val status_code: Int? = null
)