package com.deepak.headytest.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ProductsVO : Parcelable
{
    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name = ""

    @SerializedName("date_added")
    var dateAdded = ""

    @SerializedName("variants")
    var variants  = ArrayList<VariantsVO>()

    @SerializedName("tax")
    var taxs  = TaxVO()

    var viewCount = 0

    var category = ""

    constructor() {}

    constructor(parcel: Parcel) {
       id = parcel.readInt()
        name = parcel.readString()
        dateAdded = parcel.readString()
        variants = parcel.readArrayList(ProductsVO::class.java.classLoader) as ArrayList<VariantsVO>
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(dateAdded)
        parcel.writeList(variants)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductsVO> {
        override fun createFromParcel(parcel: Parcel): ProductsVO {
            return ProductsVO(parcel)
        }

        override fun newArray(size: Int): Array<ProductsVO?> {
            return arrayOfNulls(size)
        }
    }
}