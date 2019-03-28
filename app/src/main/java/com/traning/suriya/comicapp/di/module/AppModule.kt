package com.traning.suriya.comicapp.di.module

import android.app.Application
import com.traning.suriya.comicapp.ComicApplication
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule{

    @Binds
    internal abstract fun bindsAppContext(application: ComicApplication): Application
}