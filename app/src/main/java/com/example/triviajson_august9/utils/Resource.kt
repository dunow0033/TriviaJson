package com.example.triviajson_august9.utils

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Error(val message: String) : Resource<Nothing>()
    data class Success<T>(val item: T) : Resource<T>()
}
