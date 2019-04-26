package com.traning.suriya.comicapp.model.banner

import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("_id")
    val id: String,
    @SerializedName("bannerId")
    val bannerId: Int,
    @SerializedName("link")
    val link: String
)