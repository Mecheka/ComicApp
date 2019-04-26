package com.traning.suriya.comicapp.service

<<<<<<< HEAD
interface ComicAPI {

=======
import com.traning.suriya.comicapp.model.banner.BannerResponce
import com.traning.suriya.comicapp.model.chapter.ChapterResponce
import com.traning.suriya.comicapp.model.comic.ComicResponce
import com.traning.suriya.comicapp.model.link.LinkResponce
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicAPI {

    @GET("/banner")
    fun getBanner(): Single<BannerResponce>

    @GET("/comic")
    fun getAllComic(): Single<ComicResponce>

    @GET("/chapter/{comicId}")
    fun getChapterByComicId(
        @Path("comicId") comicId: Int
    ): Single<Response<ChapterResponce>>

    @GET("/link/{chapterId}")
    fun getChapterDetailByChapterId(
        @Path("chapterId") chapterId: Int
    ): Single<Response<LinkResponce>>
>>>>>>> normal

}