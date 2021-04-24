package com.hb.pages.api.google.model

import com.squareup.moshi.Json

data class Volume(
    @Json(name = "volumeInfo")
    val volumeInfo: VolumeInfo,

    @Json(name = "saleInfo")
    val saleInfo: SaleInfo? = null
)

data class VolumeInfo(
    @Json(name = "title")
    val title: String,

    @Json(name = "authors")
    val authors: List<String> = emptyList(),

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "pageCount")
    val pageCount: Int? = null,

    @Json(name = "categories")
    val categories: List<String> = emptyList(),

    @Json(name = "language")
    val language: String? = null,

    @Json(name = "imageLinks")
    val imageLinks: ImageLinks? = null,

    @Json(name = "industryIdentifiers")
    val industryIdentifier: List<IndustryIdentifier> = emptyList(),

    @Json(name = "publisher")
    val publisher: String? = null,

    @Json(name = "publishedDate")
    val publishedDate: String? = null
)

data class IndustryIdentifier(
    @Json(name = "type")
    val type: String,

    @Json(name = "identifier")
    val identifier: String
)

data class ImageLinks(
    @Json(name = "smallThumbnail")
    val smallThumbnail: String?,

    @Json(name = "thumbnail")
    val thumbnail: String?,

    @Json(name = "small")
    val small: String?,

    @Json(name = "medium")
    val medium: String?,

    @Json(name = "large")
    val large: String?,

    @Json(name = "extraLarge")
    val extraLarge: String?
)

data class SaleInfo(
    @Json(name = "buyLink")
    val buyLink: String? = null
)