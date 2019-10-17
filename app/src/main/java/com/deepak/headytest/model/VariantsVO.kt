package com.deepak.headytest.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class VariantsVO : Parcelable {

    @SerializedName("id")
    var id = 0
    @SerializedName("color")
    var color = ""
    @SerializedName("size")
    var size = ""
    @SerializedName("price")
    var price = 0

    constructor(parcel: Parcel) {
        id = parcel.readInt()
        color = parcel.readString()
        size = parcel.readString()
        price = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        if(color!=null)
            parcel.writeString(color)
        else
            parcel.writeString("")
        if(size!=null)
            parcel.writeString(size)
        else
            parcel.writeString("")
        parcel.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VariantsVO> {
        override fun createFromParcel(parcel: Parcel): VariantsVO {
            return VariantsVO(parcel)
        }

        override fun newArray(size: Int): Array<VariantsVO?> {
            return arrayOfNulls(size)
        }
    }
}