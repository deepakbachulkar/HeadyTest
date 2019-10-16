package com.deepak.headytest.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.selltm.app.networkkotlin.APIRequests

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesViewModel : ViewModel() {

    //this is the data that we will fetch asynchronously
    var categriesResponaseVO: MutableLiveData<CategoriesDataResponaseVO>? = null

      val categories: LiveData<CategoriesDataResponaseVO>
        get() {
            if (categriesResponaseVO == null) {
                categriesResponaseVO = MutableLiveData<CategoriesDataResponaseVO>()
                loadCategoies()
            }
            return categories
        }



    //This method is using Retrofit to get the JSON data from URL
    private fun loadCategoies()
    {
        APIRequests.getCategoriesAndProducts(object : Callback<CategoriesDataResponaseVO> {
            override fun onResponse(call: Call<CategoriesDataResponaseVO>, response: Response<CategoriesDataResponaseVO>)
            {
                categriesResponaseVO!!.setValue(response.body())
            }

            override fun onFailure(call: Call<CategoriesDataResponaseVO>, t: Throwable) {
                categriesResponaseVO!!.setValue(null)
            }


        })
    }
}
