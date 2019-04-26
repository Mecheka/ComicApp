package com.traning.suriya.comicapp.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.traning.suriya.comicapp.scene.chapter.ChapterViewModel
import com.traning.suriya.comicapp.scene.comic.MainViewModel
import com.traning.suriya.comicapp.scene.viewdetail.ChapterDetailViewModel
import com.traning.suriya.newsreaderv3.di.module.ViewModelFactory
import com.traning.suriya.newsreaderv3.di.module.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChapterViewModel::class)
    protected abstract fun bindsChapterViewModel(viewModel: ChapterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChapterDetailViewModel::class)
    protected abstract fun bindsChapterDetailViewModel(viewModel: ChapterDetailViewModel): ViewModel
}