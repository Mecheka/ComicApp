package com.traning.suriya.comicapp.service

import com.traning.suriya.comicapp.model.banner.BannerResponce
import com.traning.suriya.comicapp.model.chapter.ChapterResponce
import com.traning.suriya.comicapp.model.comic.ComicResponce
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicAPI {

    @GET("/banner")
    fun getBanner(): Single<BannerResponce>

    @GET("/comic")
    fun getAllComic(): Single<ComicResponce>

    @GET("/chapter/{comicId}")
    fun getChapterByComicId(
        @Path("comicId") comicId: String
    ): Single<ChapterResponce>

}