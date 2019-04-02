package com.traning.suriya.comicapp.repository

import com.traning.suriya.comicapp.model.link.LinkResponce
import com.traning.suriya.comicapp.service.ComicAPI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Singleton

@Singleton
class ChapterDetailRepository(private val service: ComicAPI){

    fun getChapterDetailByChapterId(chapterId:Int):Single<Response<LinkResponce>>{
        return service.getChapterDetailByChapterId(chapterId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}