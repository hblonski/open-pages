package com.hb.pages.api.google

import com.hb.pages.BuildConfig
import com.hb.pages.api.google.model.GoogleBooksApiOrderBy
import com.hb.pages.api.google.model.GoogleBooksApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GoogleBooksAPIClient {

    @GET("volumes")
    fun search(
        @Query("q") query: String,
        @Query("key") key: String = BuildConfig.googleBooksApiKey,
        @Query("maxResults") numberOfResults: Int,
        @Query("orderBy") orderBy: String = GoogleBooksApiOrderBy.RELEVANCE.stringValue
    ): Call<GoogleBooksApiResponse>
}