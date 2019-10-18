package com.deepak.headytest.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ProductsViewVO : Parcelable
{
    @SerializedName("id")
    var id = 0

    @SerializedName("view_count")
    var viewCount = 0

    @SerializedName("order_count")
    var orderCount = 0

    @SerializedName("shares")
    var shares = 0



    constructor() { }

    constructor(parcel: Parcel) {
       id = parcel.readInt()
        viewCount = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(viewCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductsViewVO> {
        override fun createFromParcel(parcel: Parcel): ProductsViewVO {
            return ProductsViewVO(parcel)
        }

        override fun newArray(size: Int): Array<ProductsViewVO?> {
            return arrayOfNulls(size)
        }
    }
}