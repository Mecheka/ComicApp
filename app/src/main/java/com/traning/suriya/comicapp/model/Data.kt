package com.traning.suriya.comicapp.model

import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("chapterId")
        val chapterId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("link")
        val link: String
)