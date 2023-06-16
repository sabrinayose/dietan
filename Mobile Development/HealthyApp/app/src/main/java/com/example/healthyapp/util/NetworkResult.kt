package com.example.healthyapp.util

sealed class NetworkResult<out T> {
    object Loading: NetworkResult<Nothing>()

    data class Success<out T>(
        val data: T
    ): NetworkResult<T>()

    data class Failure(
        val e: Exception
    ): NetworkResult<Nothing>()
}