package com.hb.pages.usecases.book

import com.hb.pages.api.ApiResponse


interface SearchBookUseCase {
    suspend fun search(query: String): ApiResponse
}