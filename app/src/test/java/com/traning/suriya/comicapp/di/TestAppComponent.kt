package com.traning.suriya.comicapp.di

import com.traning.suriya.comicapp.base.BaseTest
import com.traning.suriya.comicapp.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ TestNetworkModule::class, ViewModelModule::class, TestRxJavaModule::class])
interface TestAppComponent {

    fun inject(baseTest: BaseTest)
}