package com.dalmuina.data.network.ktor

import com.dalmuina.domain.model.MovieError
import com.dalmuina.domain.model.Result
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.CancellationException
import kotlinx.io.IOException

suspend fun <T> safeApiCall(
    call: suspend () -> T
): Result<T, MovieError> {
    return try {
        Result.Success(call())
    } catch (e: CancellationException) {
        throw e
    } catch (_: IOException) {
        Result.Error(MovieError.Network)
    } catch (e: ClientRequestException) {
        when (e.response.status.value) {
            404 -> Result.Error(MovieError.NotFound)
            else -> Result.Error(MovieError.Unknown)
        }
    } catch (_: ServerResponseException) {
        Result.Error(MovieError.Network)
    }
}
