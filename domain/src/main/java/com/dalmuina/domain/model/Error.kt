package com.dalmuina.domain.model

sealed interface AppError

sealed interface MovieError : AppError {
    data object Network : MovieError
    data object NotFound : MovieError
    data object Unknown : MovieError
}