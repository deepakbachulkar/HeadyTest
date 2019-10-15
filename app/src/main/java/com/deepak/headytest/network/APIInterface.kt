package com.deepak.headytest.network

import com.deepak.headytest.model.CategoriesDataResponaseVO
import okhttp3.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIInterface{
    @GET("json")
    fun getNewsList(): Call<CategoriesDataResponaseVO>
}