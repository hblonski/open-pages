package com.hb.pages.features

import com.hb.pages.model.Book

sealed class MainActivityState(
    val hasConnectionError: Boolean,
    val hasResults: Boolean,
    val isFirstSearch: Boolean
) {
    object ConnectionError : MainActivityState(
        hasConnectionError = true,
        hasResults = false,
        isFirstSearch = false
    )

    data class Results(
        val results: List<Book>
    ) : MainActivityState(
        hasConnectionError = false,
        hasResults = true,
        isFirstSearch = false
    )

    object NoResults : MainActivityState(
        hasConnectionError = false,
        hasResults = false,
        isFirstSearch = false
    )

    object FirstSearch : MainActivityState(
        hasConnectionError = false,
        hasResults = false,
        isFirstSearch = true
    )
}
