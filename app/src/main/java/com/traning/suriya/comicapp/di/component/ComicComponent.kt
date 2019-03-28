package com.traning.suriya.comicapp.di.component

import com.traning.suriya.comicapp.ComicApplication
import com.traning.suriya.comicapp.di.module.ActivityBinder
import com.traning.suriya.comicapp.di.module.AppModule
import com.traning.suriya.comicapp.di.module.NetworkModule
import com.traning.suriya.comicapp.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBinder::class,
        ViewModelModule::class]
)
interface ComicComponent : AndroidInjector<ComicApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ComicApplication>()
}
