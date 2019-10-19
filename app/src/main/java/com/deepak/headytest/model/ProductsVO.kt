package com.deepak.headytest.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ProductsVO : Parcelable
{
    @SerializedName("id")
    var id:Int = 0

    @SerializedName("name")
    var name = ""

    @SerializedName("date_added")
    var dateAdded = ""

    @SerializedName("variants")
    var variants  = ArrayList<VariantsVO>()

    @SerializedName("tax")
    var taxs  = TaxVO()

    var count:Int = 0

    var category = ""

    constructor() {}

    constructor(parcel: Parcel) {
       id = parcel.readInt()
        name = parcel.readString()
        dateAdded = parcel.readString()
        variants = parcel.readArrayList(ProductsVO::class.java.classLoader) as ArrayList<VariantsVO>
        count = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(dateAdded)
        parcel.writeList(variants)
        parcel.writeInt(count)
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