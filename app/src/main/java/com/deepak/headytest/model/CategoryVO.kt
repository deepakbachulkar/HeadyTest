package com.deepak.headytest.model

import com.google.gson.annotations.SerializedName

class CategoryVO{
    @SerializedName("id")
    var id : Int =0

    @SerializedName("name")
    var name  = ""

    @SerializedName("products")
    var products : ArrayList<ProductsVO> = ArrayList()

}