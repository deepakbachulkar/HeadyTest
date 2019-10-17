package com.deepak.headytest.model

import com.google.gson.annotations.SerializedName

class RankingsVO{
    @SerializedName("ranking")
    var ranking : String = ""

    @SerializedName("name")
    var name  = ""

    @SerializedName("products")
    var productView : ArrayList<ProductsViewVO> = ArrayList()

}