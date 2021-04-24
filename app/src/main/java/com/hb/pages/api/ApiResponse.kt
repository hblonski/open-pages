package com.hb.pages.api

sealed class ApiResponse {
    data class Success<T>(val data: T) : ApiResponse()
    data class Error<T>(val error: T) : ApiResponse()
}