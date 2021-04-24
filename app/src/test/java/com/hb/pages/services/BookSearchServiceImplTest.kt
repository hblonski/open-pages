package com.hb.pages.services

import com.hb.pages.api.ApiResponse
import com.hb.pages.api.google.GoogleBooksAPIClient
import com.hb.pages.api.google.model.ApiError
import com.hb.pages.api.google.model.GoogleBooksApiResponse
import com.hb.pages.dummyVolume
import com.hb.pages.services.api.BookSearchServiceImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse
import java.io.IOException
import java.net.HttpURLConnection

class BookSearchServiceImplTest : BaseServiceTest() {

    @MockK
    private lateinit var googleBooksAPIClient: GoogleBooksAPIClient

    private lateinit var bookSearchServiceImpl: BookSearchServiceImpl

    @Before
    fun init() {
        bookSearchServiceImpl = BookSearchServiceImpl(googleBooksAPIClient)
    }

    @Test
    fun should_returnError_when_responseNotOk() = runBlocking {
        val query = "test"
        val response = mockk<Response<GoogleBooksApiResponse>>()
        val call = mockk<Call<GoogleBooksApiResponse>>()
        coEvery { call.awaitResponse() } returns response
        every { response.code() } returns HttpURLConnection.HTTP_BAD_REQUEST
        coEvery {
            googleBooksAPIClient.search(
                query,
                numberOfResults = BookSearchServiceImpl.NUMBER_OF_RESULTS
            )
        } returns call
        val expected = ApiResponse.Error(ApiError.COULD_NOT_CONNECT)
        assertEquals(expected, bookSearchServiceImpl.search(query))
    }

    @Test
    fun should_returnSuccessWithData_when_responseOk() = runBlocking {
        val query = "test"
        val body = GoogleBooksApiResponse(listOf(dummyVolume))
        val response = mockk<Response<GoogleBooksApiResponse>>()
        val call = mockk<Call<GoogleBooksApiResponse>>()
        coEvery { call.awaitResponse() } returns response
        every { response.body() } returns body
        every { response.code() } returns HttpURLConnection.HTTP_OK
        coEvery {
            googleBooksAPIClient.search(
                query,
                numberOfResults = BookSearchServiceImpl.NUMBER_OF_RESULTS
            )
        } returns call
        val expected = ApiResponse.Success(body)
        assertEquals(expected, bookSearchServiceImpl.search(query))
    }

    @Test
    fun should_returnError_when_exceptionGettingData() = runBlocking {
        val query = "test"
        val call = mockk<Call<GoogleBooksApiResponse>>()
        val exception = mockk<IOException>()
        coEvery { call.awaitResponse() } throws exception
        every { exception.message } returns "test"
        coEvery {
            googleBooksAPIClient.search(
                query,
                numberOfResults = BookSearchServiceImpl.NUMBER_OF_RESULTS
            )
        } returns call
        val expected = ApiResponse.Error(ApiError.COULD_NOT_CONNECT)
        assertEquals(expected, bookSearchServiceImpl.search(query))
    }
}