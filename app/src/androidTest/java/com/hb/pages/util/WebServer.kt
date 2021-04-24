package com.hb.pages.util

import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.io.BufferedReader
import java.io.InputStreamReader

object WebServer {
    lateinit var baseUrl: HttpUrl
    lateinit var mockWebServer: MockWebServer

    fun start() {
        mockWebServer = MockWebServer().apply {
            start(WEB_SERVER_PORT)
        }
        baseUrl = mockWebServer.url("/")
    }

    fun shutdown() {
        mockWebServer.shutdown()
    }

    fun takeRequestAt(index: Int): RecordedRequest {
        var internalIndex = index
        lateinit var request: RecordedRequest
        do {
            request = mockWebServer.takeRequest()
        } while (--internalIndex >= 0)
        return request
    }

    fun enqueueServerResponse(statusCode: Int, fileName: String) {
        val mockResponse = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .setBody(getJsonReader(path = fileName))
            .setResponseCode(statusCode)
        mockWebServer.enqueue(mockResponse)
    }

    fun enqueueServerResponse(statusCode: Int) {
        val mockResponse = MockResponse()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .addHeader("Cache-Control", "no-cache")
            .setResponseCode(statusCode)
        mockWebServer.enqueue(mockResponse)
    }

    private const val WEB_SERVER_PORT = 8000
}

fun Any.getJsonReader(path: String): String {
    val inputStream = this.javaClass.classLoader?.getResourceAsStream(path)
    return BufferedReader(InputStreamReader(inputStream)).readText()
}