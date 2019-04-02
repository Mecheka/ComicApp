package com.traning.suriya.comicapp.model.comic

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
data class Comic(
    @SerializedName("image")
    val image: String,
    @SerializedName("mangaId")
    val mangaId: Int,
    @SerializedName("name")
    val name: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeInt(mangaId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comic> {
        override fun createFromParcel(parcel: Parcel): Comic {
            return Comic(parcel)
        }

        override fun newArray(size: Int): Array<Comic?> {
            return arrayOfNulls(size)
        }
    }

}