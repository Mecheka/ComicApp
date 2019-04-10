package com.traning.suriya.comicapp

import android.app.Application
import com.traning.suriya.comicapp.di.networkModule
import com.traning.suriya.comicapp.di.repositoryModule
import com.traning.suriya.comicapp.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ComicApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ComicApplication)
            androidLogger()
            modules(networkModule, viewmodelModule, repositoryModule)
        }
    }
}