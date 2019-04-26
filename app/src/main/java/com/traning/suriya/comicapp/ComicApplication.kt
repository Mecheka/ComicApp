package com.traning.suriya.comicapp

import com.traning.suriya.comicapp.di.component.DaggerComicComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ComicApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerComicComponent.builder().create(this)
    }
}