package com.traning.suriya.comicapp.model.comic

import com.google.gson.annotations.SerializedName
import com.traning.suriya.comicapp.base.BaseResponce

data class ComicResponce(
    @SerializedName("data")
    val data: List<Comic>
) : BaseResponce()