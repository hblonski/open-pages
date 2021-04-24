package com.hb.pages

import com.hb.pages.api.google.model.Volume
import com.hb.pages.api.google.model.VolumeInfo
import com.hb.pages.model.*
import com.hb.pages.util.NO_ID
import org.threeten.bp.LocalDate

val dummyAuthor = Author(
    id = NO_ID,
    name = "author"
)

val dummyBook = Book(
    id = NO_ID,
    title = "test",
    authors = listOf(dummyAuthor),
    description = "test",
    publishedDate = "test",
    publisher = "test",
    language = "test",
    pageCount = 123,
    imageURL = "test.com",
    isbn = "123",
    format = BookFormat.NOT_INFORMED
)

val dummyVolume = Volume(
    volumeInfo = VolumeInfo(title = "test")
)