package com.deepak.headytest

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.deepak.headytest.adapter.CategoryAdapter
import com.deepak.headytest.model.CategoriesDataResponaseVO
import com.deepak.headytest.model.CategoriesViewModel
import com.deepak.headytest.model.CategoryVO
import com.selltm.app.networkkotlin.APIRequests

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity()
{
    private var categories = ArrayList<CategoryVO>()
    private lateinit var rcvCategries:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init()
        loadCategories()
//        getDataShow()
    }

    fun init(){
        rcvCategries = findViewById(R.id.rcv_categories)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getDataShow(){
        val model:CategoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        model.categories.observe(this, object:Observer<CategoriesDataResponaseVO>{
            override fun onChanged(t: CategoriesDataResponaseVO?) {
                Log.d("Data", "Response: "+ t)
            }
        })
    }


    //This method is using Retrofit to get the JSON data from URL
    private fun loadCategories()
    {
        APIRequests.getCategoriesAndProducts(object : Callback<CategoriesDataResponaseVO> {
            override fun onResponse(call: Call<CategoriesDataResponaseVO>, response: Response<CategoriesDataResponaseVO>)
            {
                Log.i("Data", "Response: "+ response)
                if(response.code() == 200 &&  response.body()!=null &&  response.body()!!.categories!=null) {
                    categories = response.body()!!.categories
                    setRvAdapter(categories)
                }
            }

            override fun onFailure(call: Call<CategoriesDataResponaseVO>, t: Throwable) {
                Log.i("Data", "Error: "+ t.printStackTrace()) }
        })
    }

    private fun setRvAdapter(list: ArrayList<CategoryVO>) {
        // set up the RecyclerView
        rcvCategries.layoutManager = LinearLayoutManager(this@MainActivity)
        val adapter = CategoryAdapter(this@MainActivity, object :  CategoryAdapter.ICategory {
            override fun onItemClick(position: Int) {

            }}, list)
                val verticalDecoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.HORIZONTAL)
        verticalDecoration.setDrawable(ContextCompat.getDrawable(this@MainActivity!!, R.drawable.line)!!)
        rcvCategries.addItemDecoration(verticalDecoration)
        rcvCategries.adapter = adapter
    }
}
