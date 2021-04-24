package com.hb.pages.api.google.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoogleBooksApiResponse(
    @Json(name = "items")
    val items: List<Volume>? = null
)