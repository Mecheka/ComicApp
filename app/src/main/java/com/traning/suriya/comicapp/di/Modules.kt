package com.traning.suriya.comicapp.di

import com.traning.suriya.comicapp.repository.ChapterDetailRepository
import com.traning.suriya.comicapp.repository.ChapterRepository
import com.traning.suriya.comicapp.repository.MainRepository
import com.traning.suriya.comicapp.scene.chapter.ChapterViewModel
import com.traning.suriya.comicapp.scene.comic.MainActivity
import com.traning.suriya.comicapp.scene.comic.MainViewModel
import com.traning.suriya.comicapp.scene.viewdetail.ChapterDetailActivity
import com.traning.suriya.comicapp.scene.viewdetail.ChapterDetailViewModel
import com.traning.suriya.comicapp.service.Common
import com.traning.suriya.comicapp.service.NetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { NetworkClient(androidContext()) }
    single { Common(get()).getAllComic() }
}

val viewmodelModule = module {
    viewModel {(repository: MainRepository) ->MainViewModel(repository)}
    viewModel { ChapterViewModel(repository = get()) }
    viewModel { ChapterDetailViewModel(repository = get()) }
}

val repositoryModule = module {
    factory { ChapterRepository(service = get()) }
    factory { ChapterDetailRepository(service = get()) }
    scope(named<MainActivity>()){
        scoped {
            MainRepository(service = get())
        }
    }
}