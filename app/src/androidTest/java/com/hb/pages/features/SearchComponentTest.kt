package com.hb.pages.features

import androidx.test.espresso.intent.rule.IntentsTestRule
import com.hb.pages.BaseComponentTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchComponentTest : BaseComponentTest() {

    private lateinit var robot: SearchRobot

    @get:Rule
    var activityTestRule = IntentsTestRule(MainActivity::class.java, true, false)

    @Before
    fun before() {
        robot = SearchRobot(this)
    }

    @Test
    fun should_displayFirstSearchTip_when_screenOpened() {
        robot
            .whenLaunchActivity()
            .thenFirstTimeInfoDisplayed()
    }

    @Test
    fun should_displayNotFoundInfo_when_searchResultsNotFound() {
        robot
            .whenLaunchActivity()
            .whenNoResultsAvailable()
            .whenPerformSearch()
            .thenNoResultsInfoDisplayed()
    }

    @Test
    fun should_displaySearchResults_when_searchResultsFound() {
        robot
            .whenLaunchActivity()
            .whenResultsAvailable()
            .whenPerformSearch()
            .thenResultsDisplayed()
    }
}