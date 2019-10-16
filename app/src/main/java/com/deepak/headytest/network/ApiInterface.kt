package com.selltm.app.networkkotlin

import com.deepak.headytest.model.CategoriesDataResponaseVO
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Deepak
 */
interface ApiInterface
{
    @GET("/json")
    fun  getCategoriesAndProducts(): Call<CategoriesDataResponaseVO>



}
