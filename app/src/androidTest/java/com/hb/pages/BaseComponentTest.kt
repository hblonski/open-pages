package com.hb.pages

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hb.pages.util.WebServer
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@Ignore("Base class")
open class BaseComponentTest : KoinTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupTests() {
            WebServer.start()
        }

        @AfterClass
        @JvmStatic
        fun tearDownTests() {
            WebServer.shutdown()
        }
    }
}