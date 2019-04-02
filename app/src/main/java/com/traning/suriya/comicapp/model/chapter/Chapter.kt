package com.traning.suriya.comicapp.model.chapter

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
data class Chapter(
    @SerializedName("id")
    val id: Int,
    @SerializedName("mongaId")
    val mongaId: Int,
    @SerializedName("name")
    val name: String
) : Comparable<Chapter>, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun compareTo(other: Chapter): Int {
        return when {
            id < other.id -> -1
            id == other.id -> 0
            else -> 1
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(mongaId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Chapter> {
        override fun createFromParcel(parcel: Parcel): Chapter {
            return Chapter(parcel)
        }

        override fun newArray(size: Int): Array<Chapter?> {
            return arrayOfNulls(size)
        }
    }

}