package com.traning.suriya.comicapp.service

class Common(val networkClient: NetworkClient){
    fun getAllComic(): ComicAPI{
        return networkClient.create().create(ComicAPI::class.java)
    }
}