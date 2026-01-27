package com.dalmuina.data.remote

import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException

suspend fun <T> safeApiCall(
    call: suspend () -> T
): Result<T, MovieError> {
    return try {
        Result.Success(call())
    } catch (e: kotlinx.io.IOException) {
        Result.Error(MovieError.Network)
    } catch (e: ClientRequestException) {
        when (e.response.status.value) {
            404 -> Result.Error(MovieError.NotFound)
            else -> Result.Error(MovieError.Unknown)
        }
    } catch (e: ServerResponseException) {
        Result.Error(MovieError.Network)
    }
}
