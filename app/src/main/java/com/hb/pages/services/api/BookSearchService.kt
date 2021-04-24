package com.hb.pages.services.api

import com.hb.pages.api.ApiResponse

interface BookSearchService {
    suspend fun search(term: String): ApiResponse
}