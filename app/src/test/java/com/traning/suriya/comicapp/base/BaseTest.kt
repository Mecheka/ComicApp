package com.traning.suriya.comicapp.base

import android.arch.lifecycle.ViewModelProvider
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import com.traning.suriya.comicapp.di.DaggerTestAppComponent
import com.traning.suriya.comicapp.di.TestAppComponent
import com.traning.suriya.comicapp.di.TestNetworkModule
import com.traning.suriya.comicapp.di.TestRxJavaModule
import org.junit.After
import org.junit.Before
import java.io.File
import javax.inject.Inject

abstract class BaseTest {

    lateinit var testAppComponent: TestAppComponent
    lateinit var mockServer: MockWebServer
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Before
    open fun setUp() {
        this.configureMockServer()
        this.configureDi()
    }

    @After
    open fun tearDown(){
        this.stopServer()
    }
    open fun configureDi() {
        this.testAppComponent = DaggerTestAppComponent.builder()
            .testNetworkModule(TestNetworkModule(mockServer.url("/").toString()))
            .testRxJavaModule(TestRxJavaModule())
            .build()
        this.testAppComponent.inject(this)
    }

    open fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }

    open fun stopServer(){
        mockServer.shutdown()
    }

    open fun mockHttpResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName)))

    private fun getJson(path : String) : String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}