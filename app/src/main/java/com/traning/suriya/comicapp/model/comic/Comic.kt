package com.traning.suriya.comicapp.model.comic

import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("_id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("mangaId")
    val mangaId: Int,
    @SerializedName("name")
    val name: String
)