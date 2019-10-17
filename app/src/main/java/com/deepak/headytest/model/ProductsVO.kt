package com.deepak.headytest.model

import com.google.gson.annotations.SerializedName

class ProductsVO {
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

}