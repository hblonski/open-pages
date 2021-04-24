package com.hb.pages.usecases.book

import com.hb.pages.BaseTest
import com.hb.pages.api.ApiResponse
import com.hb.pages.api.google.model.GoogleBooksApiResponse
import com.hb.pages.services.api.BookSearchService
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchBookUseCaseImplTest : BaseTest() {

    private lateinit var searchBookUseCase: SearchBookUseCaseImpl

    @MockK
    private lateinit var bookSearchService: BookSearchService

    @Before
    fun before() {
        searchBookUseCase = SearchBookUseCaseImpl(
            bookSearchService
        )
    }

    @Test
    fun should_returnSuccess_when_searchSuccessful() = runBlocking {
        val query = "abc"
        val expected = ApiResponse.Success(
            GoogleBooksApiResponse()
        )
        coEvery { bookSearchService.search(query) } returns expected
        assertEquals(expected, searchBookUseCase.search(query))
    }

    @Test
    fun should_returnError_when_errorSearching() = runBlocking {
        val query = "abc"
        val expected = ApiResponse.Error("")
        coEvery { bookSearchService.search(query) } returns expected
        assertEquals(expected, searchBookUseCase.search(query))
    }
}