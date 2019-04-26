package com.traning.suriya.comicapp.repository

import com.traning.suriya.comicapp.model.chapter.ChapterResponce
import com.traning.suriya.comicapp.service.ComicAPI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class ChapterRepository(private val service: ComicAPI) {

    fun getChapterByComicId(mangaId: Int): Single<Response<ChapterResponce>> {
        return service.getChapterByComicId(mangaId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}