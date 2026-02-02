package com.dalmuina.domain.model

sealed interface MovieError {
    data object Network : MovieError
    data object NotFound : MovieError
    data object Unknown : MovieError
}