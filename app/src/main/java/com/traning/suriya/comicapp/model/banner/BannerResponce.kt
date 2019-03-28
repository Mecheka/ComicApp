package com.traning.suriya.comicapp.model.banner

import com.google.gson.annotations.SerializedName
import com.traning.suriya.comicapp.base.BaseResponce

data class BannerResponce(
    @SerializedName("data")
    val data: List<Banner>
) : BaseResponce()