package com.traning.suriya.comicapp.model.chapter

import com.google.gson.annotations.SerializedName

data class Chapter(
    @SerializedName("id")
    val id: Int,
    @SerializedName("mongaId")
    val mongaId: Int,
    @SerializedName("name")
    val name: String
)