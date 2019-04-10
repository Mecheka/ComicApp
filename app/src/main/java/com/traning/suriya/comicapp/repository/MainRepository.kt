package com.traning.suriya.comicapp.repository

import com.traning.suriya.comicapp.model.banner.BannerResponce
import com.traning.suriya.comicapp.model.combine.BannerAndComic
import com.traning.suriya.comicapp.model.comic.ComicResponce
import com.traning.suriya.comicapp.service.ComicAPI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class MainRepository(private val service: ComicAPI) {

    fun getComicAndBanner(): Single<BannerAndComic> {
        return Single.zip(getBanner(), getAllComic(), BiFunction { banner, comic ->
            BannerAndComic(banner, comic)
        })
    }

    private fun getBanner(): Single<BannerResponce> {
        return service.getBanner()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    private fun getAllComic(): Single<ComicResponce> {
        return service.getAllComic()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}