package com.traning.suriya.comicapp.model.chapter

import com.google.gson.annotations.SerializedName
import com.traning.suriya.comicapp.base.BaseResponce

data class ChapterResponce(
    @SerializedName("data")
    val data: List<Chapter>
) : BaseResponce()