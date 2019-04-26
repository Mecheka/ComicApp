package com.traning.suriya.comicapp.base

import com.squareup.okhttp.mockwebserver.MockWebServer
import com.traning.suriya.comicapp.di.networkModule
import com.traning.suriya.comicapp.di.repositoryModule
import com.traning.suriya.comicapp.di.viewmodelModule
import org.junit.After
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.test.KoinTest

abstract class BaseTest: KoinTest {

    lateinit var mockServer: MockWebServer

    @Before
    open fun setUp(){
        this.configureMockServer()
    }

    @After
    open fun tearDown(){
        this.stopServer()
    }

    open fun configureMockServer(){
        mockServer= MockWebServer()
        mockServer.start()
    }

    open fun stopServer(){
        mockServer.shutdown()
    }

    open fun configureDi(){
        startKoin {
            listOf(networkModule, viewmodelModule, repositoryModule)
        }
    }
}