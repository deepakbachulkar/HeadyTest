package com.deepak.headytest.model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class CategoriesDataResponaseVO{
    @SerializedName("categories")
    var categories = ArrayList<CategoryVO>()
}