package com.hb.pages.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf

inline fun <reified T : Activity> waitUntilActivityVisible() {
    val startTime = System.currentTimeMillis()
    while (!isVisible<T>()) {
        Thread.sleep(CONDITION_CHECK_INTERVAL)
        if (System.currentTimeMillis() - startTime >= TIMEOUT) {
            throw AssertionError("Activity ${T::class.java.simpleName} not visible after $TIMEOUT milliseconds.")
        }
    }
}

@SuppressWarnings("SwallowedException")
inline fun <reified T : Activity> isVisible(): Boolean {
    val am = InstrumentationRegistry.getInstrumentation()
        .targetContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val visibleActivityName = try {
        am.appTasks[0].taskInfo.topActivity?.className
    } catch (e: IllegalStateException) {
        // Sometimes the above method return this exception,because it runs on a loop. In this
        // case, the next iteration should be run
        "Error opening activity."
    }
    return visibleActivityName == T::class.java.name
}

const val TIMEOUT = 6000L
const val CONDITION_CHECK_INTERVAL = 100L

fun withIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        private var currentIndex = 0

        override fun describeTo(description: Description) {
            description.appendText("with index: ")
            description.appendValue(index)
            matcher.describeTo(description)
        }

        override fun matchesSafely(view: View): Boolean {
            return matcher.matches(view) && currentIndex++ == index
        }
    }
}

fun selectTabAtPosition(tabIndex: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription() = "With tab at index $tabIndex"

        override fun getConstraints() = allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

        override fun perform(uiController: UiController, view: View) {
            val tabLayout = view as TabLayout
            val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                ?: throw PerformException.Builder()
                    .withCause(Throwable("No tab at index $tabIndex"))
                    .build()

            tabAtIndex.select()
        }
    }
}

fun withRecyclerViewChildAt(
    @IdRes recyclerViewRes: Int,
    @IdRes childId: Int,
    position: Int
): Matcher<View> {
    return RecyclerViewMatcher(recyclerViewRes)
        .atPositionOnView(position, childId)
}