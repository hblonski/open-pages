package com.hb.pages.api.google.mapper

import com.hb.pages.api.google.model.ImageLinks
import com.hb.pages.api.google.model.IndustryIdentifier
import com.hb.pages.api.google.model.IndustryIdentifierType
import com.hb.pages.api.google.model.Volume
import com.hb.pages.mapper.Mapper
import com.hb.pages.model.Author
import com.hb.pages.model.Book
import com.hb.pages.model.BookFormat

class GoogleBooksVolumeMapper : Mapper<Volume, Book> {
    override fun map(from: Volume): Book {
        return Book(
            title = from.volumeInfo.title,
            publishedDate = from.volumeInfo.publishedDate,
            description = from.volumeInfo.description,
            publisher = from.volumeInfo.publisher,
            isbn = getIdentifier(from.volumeInfo.industryIdentifier),
            pageCount = from.volumeInfo.pageCount ?: 1,
            imageURL = getImageURL(from.volumeInfo.imageLinks),
            language = from.volumeInfo.language,
            authors = from.volumeInfo.authors.map { Author(name = it) },
            format = BookFormat.NOT_INFORMED,
        )
    }

    // It is not necessary to unmap Volumes, since this app does not send data to the server (yet)
    override fun unmap(from: Book): Volume {
        throw NotImplementedError("Not implemented.")
    }

    // Google Books API does not have a pattern of which ISBN format is going to be sent. This
    // method tries to extract the ISBN following a defined priority order.
    private fun getIdentifier(identifiers: List<IndustryIdentifier>): String {
        val isbn13 = identifiers.firstOrNull {
            it.type == IndustryIdentifierType.ISBN_13.code
        }
        if (isbn13 != null) {
            return isbn13.identifier
        }
        val isbn10 = identifiers.firstOrNull {
            it.type == IndustryIdentifierType.ISBN_10.code
        }
        if (isbn10 != null) {
            return isbn10.identifier
        }
        val issn = identifiers.firstOrNull {
            it.type == IndustryIdentifierType.ISSN.code
        }
        if (issn != null) {
            return issn.identifier
        }
        val other = identifiers.firstOrNull {
            it.type == IndustryIdentifierType.OTHER.code
        }
        if (other != null) {
            return other.identifier
        }
        return ""
    }

    private fun getImageURL(urls: ImageLinks?): String? {
        return urls?.let {
            val url = extractURL(urls)
            return url?.let {
                // Most URLs sent by Google Books API do not include https in them, but they
                // support it, so it is manually added.
                if (!url.contains(HTTPS)) {
                    url.replace(HTTP, HTTPS)
                } else url
            }
        }
    }

    // Google Books API does not have a pattern of which URL is going to be sent. This method
    // tries to extract the URL following a defined priority order.
    @Suppress("ComplexMethod")
    private fun extractURL(urls: ImageLinks): String? {
        if (!urls.medium.isNullOrEmpty()) {
            return urls.medium
        }
        if (!urls.large.isNullOrEmpty()) {
            return urls.large
        }
        if (!urls.extraLarge.isNullOrEmpty()) {
            return urls.extraLarge
        }
        if (!urls.small.isNullOrEmpty()) {
            return urls.small
        }
        if (!urls.thumbnail.isNullOrEmpty()) {
            return urls.thumbnail
        }
        if (!urls.smallThumbnail.isNullOrEmpty()) {
            return urls.smallThumbnail
        }
        return null
    }

    companion object {
        private const val HTTP = "http"
        private const val HTTPS = "https"
    }
}