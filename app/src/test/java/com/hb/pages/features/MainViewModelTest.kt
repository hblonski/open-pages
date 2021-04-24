package com.hb.pages.features

import com.hb.pages.BaseTest
import com.hb.pages.api.ApiResponse
import com.hb.pages.api.google.mapper.GoogleBooksVolumeMapper
import com.hb.pages.api.google.model.GoogleBooksApiResponse
import com.hb.pages.dummyVolume
import com.hb.pages.usecases.book.SearchBookUseCase
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewModelTest : BaseTest() {

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var searchBookUseCase: SearchBookUseCase

    private val googleBooksVolumeMapper = GoogleBooksVolumeMapper()

    @Before
    fun before() {
        viewModel = MainViewModel(
            searchBookUseCase,
            googleBooksVolumeMapper
        )
    }

    @Test
    fun should_initViewModelWithFirstSearchState() {
        assertEquals(MainActivityState.FirstSearch, viewModel.state.value)
    }

    @Test
    fun should_setStateToNoResults_when_queryIsEmpty() = runBlocking {
        viewModel.search("")
        assertEquals(MainActivityState.NoResults, viewModel.state.value)
    }

    @Test
    fun should_setStateToConnectionError_when_errorSearchingBooks() = runBlocking {
        val query = "book"
        coEvery { searchBookUseCase.search(query) } returns mockk<ApiResponse.Error<Unit>>()
        viewModel.search(query)
        assertEquals(MainActivityState.ConnectionError, viewModel.state.value)
    }

    @Test
    fun should_setStateToNoResults_when_noSearchResults() = runBlocking {
        val query = "book"
        val results = ApiResponse.Success(
            data = GoogleBooksApiResponse(items = emptyList())
        )
        coEvery { searchBookUseCase.search(query) } returns results
        viewModel.search(query)
        assertEquals(MainActivityState.NoResults, viewModel.state.value)
    }

    @Test
    fun should_setStateToResults_when_resultsFound() = runBlocking {
        val query = "book"
        val results = ApiResponse.Success(
            data = GoogleBooksApiResponse(
                items = listOf(
                    dummyVolume,
                    dummyVolume
                )
            )
        )
        coEvery { searchBookUseCase.search(query) } returns results
        viewModel.search(query)

        val expectedItems = results.data.items?.map {
            GoogleBooksVolumeMapper().map(it)
        }

        assertEquals(MainActivityState.Results(expectedItems!!), viewModel.state.value)
    }
}