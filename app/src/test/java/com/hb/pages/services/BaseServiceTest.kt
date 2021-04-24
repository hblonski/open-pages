package com.hb.pages.services

import com.hb.pages.BaseTest
import io.mockk.mockkStatic
import org.junit.Before

open class BaseServiceTest : BaseTest() {
    @Before
    fun before() {
        // If the extensions are not mocked, await() causes test to hang indefinitely
        mockkStatic("retrofit2.KotlinExtensions")
    }
}