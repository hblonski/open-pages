package com.hb.pages.services.api

import android.util.Log
import com.hb.pages.api.ApiResponse
import com.hb.pages.api.google.GoogleBooksAPIClient
import com.hb.pages.api.google.model.ApiError
import retrofit2.awaitResponse
import java.io.IOException
import java.net.HttpURLConnection

class BookSearchServiceImpl(
    private val googleBooksAPIClient: GoogleBooksAPIClient
) : BookSearchService {
    override suspend fun search(term: String): ApiResponse {
        return try {
            val response =
                googleBooksAPIClient.search(term, numberOfResults = NUMBER_OF_RESULTS).awaitResponse()
            if (response.code() == HttpURLConnection.HTTP_OK) {
                ApiResponse.Success(data = response.body())
            } else {
                Log.d(this::class.simpleName, "Google Books API returned ${response.code()}")
                ApiResponse.Error(ApiError.COULD_NOT_CONNECT)
            }
        } catch (e: IOException) {
            Log.d(this::class.simpleName, "Call failed with exception ${e.message}")
            ApiResponse.Error(ApiError.COULD_NOT_CONNECT)
        }
    }

    companion object {
        const val NUMBER_OF_RESULTS = 21
    }
}