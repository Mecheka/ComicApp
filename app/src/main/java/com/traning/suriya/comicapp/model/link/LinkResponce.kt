package com.traning.suriya.comicapp.model.link

import com.google.gson.annotations.SerializedName
import com.traning.suriya.comicapp.base.BaseResponce

data class LinkResponce(
        @SerializedName("data")
        val data: List<Link>
):BaseResponce()