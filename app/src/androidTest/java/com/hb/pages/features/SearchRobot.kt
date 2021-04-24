package com.hb.pages.features

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.hb.pages.R
import com.hb.pages.util.BaseRobot
import com.hb.pages.util.WebServer.enqueueServerResponse
import org.junit.Assert.fail

class SearchRobot(
    private val searchComponentTest: SearchComponentTest
) : BaseRobot<SearchRobot>() {

    fun whenLaunchActivity() = apply {
        searchComponentTest.activityTestRule.launchActivity(Intent())
    }

    fun thenFirstTimeInfoDisplayed() = apply {
        checkViewDisplayed(R.id.imageFirstSearch)
        checkViewDisplayed(R.id.textFirstSearch)
    }

    fun whenPerformSearch() = apply {
        val query = "book"
        typeTextOnView(R.id.inputSearch, 0, query)
        checkViewHasText(R.id.inputSearch, 0, query)
        pressEnterKey(R.id.inputSearch, 0)
    }

    fun whenNoResultsAvailable() = apply {
        enqueueServerResponse(200, "search/books_api_no_response.json")
    }

    fun whenResultsAvailable() = apply {
        enqueueServerResponse(200, "search/books_api_response.json")
    }

    fun thenResultsDisplayed() = apply {
        // "It" is the first book returned by the mock response
        waitForViewWithText("It")
        val recyclerView = searchComponentTest
            .activityTestRule
            .activity
            .findViewById(R.id.rvResults) as RecyclerView
        recyclerView.adapter?.itemCount?.let { it > 0 } ?: fail()
        checkRecyclerViewItemDisplayed(R.id.rvResults, R.id.viewHolderCover, 0)
    }

    fun thenNoResultsInfoDisplayed() = apply {
        waitForViewWithText(getStringById(R.string.label_search_results_not_found))
        checkViewDisplayed(R.id.imageNotFound)
        checkViewDisplayed(R.id.textNotFound)
    }
}