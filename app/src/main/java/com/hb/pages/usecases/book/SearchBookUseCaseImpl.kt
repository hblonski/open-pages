package com.hb.pages.usecases.book

import com.hb.pages.api.ApiResponse
import com.hb.pages.services.api.BookSearchService

class SearchBookUseCaseImpl(
    private val bookSearchService: BookSearchService
) : SearchBookUseCase {
    override suspend fun search(query: String): ApiResponse {
        return bookSearchService.search(query)
    }
}