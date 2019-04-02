package com.traning.suriya.comicapp.model.link

import com.google.gson.annotations.SerializedName

data class Link(
        @SerializedName("chapterId")
        val chapterId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("link")
        val link: String
)