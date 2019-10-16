package com.selltm.app.networkkotlin


import com.deepak.headytest.model.CategoriesDataResponaseVO
import com.deepak.headytest.network.APIClient
import retrofit2.Callback

class APIRequests {

    companion object {

     fun getCategoriesAndProducts(callback: Callback<CategoriesDataResponaseVO>) {
            var apiServices = APIClient.client.create(ApiInterface::class.java)
            val call = apiServices.getCategoriesAndProducts()
            call.enqueue(callback)
        }
    }


}
