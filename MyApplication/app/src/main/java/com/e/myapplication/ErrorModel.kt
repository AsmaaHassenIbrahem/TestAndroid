package com.e.myapplication

import android.os.Parcel
import android.os.Parcelable


data class ErrorModel(
    val title: String?, val Description: String?,
    val img:Int, val buttonAction: String?, val code:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(Description)
        parcel.writeInt(img)
        parcel.writeString(buttonAction)
        parcel.writeInt(code)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ErrorModel> {
        override fun createFromParcel(parcel: Parcel): ErrorModel {
            return ErrorModel(parcel)
        }

        override fun newArray(size: Int): Array<ErrorModel?> {
            return arrayOfNulls(size)
        }
    }
}