package com.hb.pages

import com.hb.pages.coroutines.CoroutineRunner
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutineTestRule : TestWatcher() {

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
        CoroutineRunner.instance.runner =
            { _, action -> CompletableDeferred(runBlocking(Dispatchers.Default, block = action)) }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        CoroutineRunner.instance.runner = null
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}