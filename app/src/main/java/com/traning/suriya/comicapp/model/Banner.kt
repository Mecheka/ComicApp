package com.traning.suriya.comicapp.model

import com.google.gson.annotations.SerializedName

data class Banner(
        @SerializedName("data")
        val data: List<Data>,
        @SerializedName("status")
        val status: Boolean
)