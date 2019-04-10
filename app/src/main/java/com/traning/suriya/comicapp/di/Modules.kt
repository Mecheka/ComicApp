package com.traning.suriya.comicapp.di

import com.traning.suriya.comicapp.repository.ChapterRepository
import com.traning.suriya.comicapp.repository.MainRepository
import com.traning.suriya.comicapp.scene.chapter.ChapterViewModel
import com.traning.suriya.comicapp.scene.comic.MainViewModel
import com.traning.suriya.comicapp.service.Common
import com.traning.suriya.comicapp.service.NetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { NetworkClient(androidContext()) }
    single { Common(get()).getAllComic() }
}

val viewmodelModule = module {
    viewModel { MainViewModel(repository = get()) }
    viewModel { ChapterViewModel(repository = get()) }
}

val repositoryModule = module {
    factory { MainRepository(service = get()) }
    factory { ChapterRepository(service = get()) }
}