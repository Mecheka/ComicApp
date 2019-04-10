package com.traning.suriya.comicapp

import com.traning.suriya.comicapp.di.component.DaggerComicComponent
import com.traning.suriya.comicapp.di.networkModule
import com.traning.suriya.comicapp.di.repositoryModule
import com.traning.suriya.comicapp.di.viewmodelModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ComicApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerComicComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ComicApplication)
            androidLogger()
            modules(networkModule, viewmodelModule, repositoryModule)
        }
    }
}