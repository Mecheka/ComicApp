package com.traning.suriya.comicapp.base

import com.google.gson.annotations.SerializedName

open class BaseResponce {
    @SerializedName("status")
    var status: Boolean? = null
    @SerializedName("message")
    var message: String? = null
}

