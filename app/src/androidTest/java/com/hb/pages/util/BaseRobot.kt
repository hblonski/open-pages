package com.hb.pages.util

import android.app.Activity
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.Matchers.not
import org.junit.Assert

@Suppress("UnnecessaryAbstractClass")
abstract class BaseRobot<T : BaseRobot<T>> {

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    fun clickOnView(@IdRes viewId: Int, index: Int = 0): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).perform(ViewActions.click())
        return this
    }

    fun checkViewDisplayed(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).check(matches(ViewMatchers.isDisplayed()))
        return this
    }

    fun checkViewHidden(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).check(matches(not(ViewMatchers.isDisplayed())))
        return this
    }

    fun checkViewDoesNotExist(@IdRes viewId: Int, index: Int = 0): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).check(doesNotExist())
        return this
    }

    fun checkRecyclerViewItemDisplayed(
        @IdRes recyclerViewId: Int,
        @IdRes viewId: Int,
        position: Int
    ): BaseRobot<T> = apply {
        onView(withRecyclerViewChildAt(recyclerViewId, viewId, position))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    fun checkViewHasText(@IdRes viewId: Int, index: Int, text: String): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).check(matches(withText(text)))
        return this
    }

    fun checkButtonEnabled(@IdRes buttonId: Int): BaseRobot<T> {
        onView(withId(buttonId)).check(matches(isEnabled()))
        return this
    }

    fun checkButtonDisabled(@IdRes buttonId: Int): BaseRobot<T> {
        onView(withId(buttonId)).check(matches(not(isEnabled())))
        return this
    }

    fun typeTextOnView(@IdRes viewId: Int, index: Int, text: String): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).perform(typeText(text))
        return this
    }

    fun pressEnterKey(@IdRes viewId: Int, index: Int): BaseRobot<T> {
        onView(withIndex(withId(viewId), index)).perform(pressImeActionButton())
        return this
    }

    fun waitForViewWithText(text: String) {
        val title = device.wait(Until.findObject(By.text(text)), TIMEOUT)
        Assert.assertNotNull("Could not find view with text $text after $TIMEOUT ms", title)
        Assert.assertEquals(
            "Could not find view with text $text after $TIMEOUT ms",
            text,
            title.text
        )
    }

    fun scrollTo(@IdRes viewId: Int): BaseRobot<T> {
        onView(withId(viewId)).perform(scrollTo())
        return this
    }

    fun assertActivityIsFinishing(
        activityTestRule: IntentsTestRule<out AppCompatActivity>
    ): BaseRobot<T> {
        Assert.assertTrue(activityTestRule.activity.isFinishing)
        return this
    }

    fun getStringById(@StringRes stringId: Int, vararg args: Any): String {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        if (args.isNotEmpty()) {
            return context.getString(stringId, *args)
        }
        return context.getString(stringId)
    }

    fun selectTabAt(@IdRes tabLayoutId: Int, index: Int): BaseRobot<T> {
        onView(withId(tabLayoutId)).perform(selectTabAtPosition(index))
        return this
    }

    inline fun <reified A : Activity> checkActualScreen(): BaseRobot<T> {
        intended(hasComponent(A::class.java.name))
        return this
    }
}