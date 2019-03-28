package com.traning.suriya.comicapp.di.module

import com.traning.suriya.comicapp.scene.chapter.ChapterActivity
import com.traning.suriya.comicapp.scene.comic.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinder{

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesChapterActivity(): ChapterActivity
}