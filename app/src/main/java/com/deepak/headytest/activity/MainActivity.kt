package com.deepak.headytest.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.deepak.headytest.adapter.CategoryAdapter
import com.selltm.app.networkkotlin.APIRequests

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import android.view.View
import android.widget.Toast
import com.deepak.headytest.R
import com.deepak.headytest.Utils.Constants
import com.deepak.headytest.adapter.RankingsAdapter
import com.deepak.headytest.databinding.ActivityProductDetailsBinding
import com.deepak.headytest.model.*
import kotlinx.android.synthetic.main.test.view.*
import okhttp3.internal.Util


class MainActivity : AppCompatActivity()
{
    private var categories = ArrayList<CategoryVO>()
    private var rankings = ArrayList<RankingsVO>()
    lateinit var bindingLayout: com.deepak.headytest.databinding.ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        loadCategories()
    }

    fun init(){
        bindingLayout = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main) as com.deepak.headytest.databinding.ActivityMainBinding
        bindingLayout.toolbar.title = resources.getString(R.string.app_name)
    }

    //This method is using Retrofit to get the JSON data from URL
    private fun loadCategories()
    {
        bindingLayout.includeContent.progressBar.visibility = View.VISIBLE
        APIRequests.getCategoriesAndProducts(object : Callback<CategoriesDataResponaseVO> {
            override fun onResponse(call: Call<CategoriesDataResponaseVO>, response: Response<CategoriesDataResponaseVO>)
            {
                bindingLayout.includeContent.progressBar.visibility = View.GONE
                Log.i("Data", "Response: "+ response)
                if(response.code() == 200 &&  response.body()!=null) {
                    categories = response.body()!!.categories
                    rankings = response.body()!!.rankings
                    Log.d("MainA", "Category count: "+ categories.size)
                    Log.d("MainA", "Ranking count: "+ rankings.size)

                    setRvAdapter()
                    setRvAdapterView()

                    categories()
                    getProductfromRanking()


                }else{
                    Toast.makeText(this@MainActivity, "Unable to connect server! Please try again later.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CategoriesDataResponaseVO>, t: Throwable) {
                bindingLayout.includeContent.progressBar.visibility = View.GONE
                Log.i("Data", "Error: "+ t.printStackTrace())
                Toast.makeText(this@MainActivity, "Unable to connect server! Please try again later.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setRvAdapter()
    {
        bindingLayout.includeContent.tvTitle.visibility = View.VISIBLE
        // set up the RecyclerView
        bindingLayout.includeContent.rvCategories.layoutManager = LinearLayoutManager(this@MainActivity)
        val adapter = CategoryAdapter(this@MainActivity, object :  CategoryAdapter.ICategory {
            override fun onItemClick(position: Int) {
                val intents = Intent(this@MainActivity, ProductsActivity:: class.java)
                intents.putParcelableArrayListExtra(Constants.KEY_DATA, categoriesNotEmpty.get(position).products)
                intents.putExtra(Constants.KEY_TITLE, categoriesNotEmpty.get(position).name)
                startActivity(intents)
            }}, categoriesNotEmpty)
        bindingLayout.includeContent.rvCategories.adapter = adapter
    }

    private var categoriesNotEmpty = ArrayList<CategoryVO>()
    private var productViewMost = ArrayList<ProductsVO>()
    private var hashMapDataView = HashMap<String, ArrayList<ProductsVO>>()
    private fun categories()
    {
        for(i in 0..categories.size-1)
        {
            if( categories.get(i).products.size>0)
            {
                categoriesNotEmpty.add(categories.get(i))

            }
        }
    }

    private  fun  getProductfromRanking()
    {
        hashMapDataView = HashMap<String, ArrayList<ProductsVO>>()
        for(i in 0.. rankings.size-1)
        {
                var arrayList = ArrayList<ProductsVO>()
                for(j in 0.. rankings.get(i).productView.size-1)
                {
                    var productsVO:ProductsVO? = null
                    if(rankings.get(i).productView.get(j).viewCount>0)
                        productsVO = getProduct(rankings.get(i).productView.get(j).id, rankings.get(i).productView.get(j).viewCount)
                    if(rankings.get(i).productView.get(j).orderCount>0)
                        productsVO = getProduct(rankings.get(i).productView.get(j).id, rankings.get(i).productView.get(j).orderCount)
                    if(rankings.get(i).productView.get(j).shares>0)
                        productsVO = getProduct(rankings.get(i).productView.get(j).id, rankings.get(i).productView.get(j).shares)
                    if (productsVO != null)
                            arrayList.add(productsVO)
                }
                hashMapDataView.put(rankings.get(i).ranking, arrayList)
        }
    }

    private fun getProduct(id:Int, rank:Int) : ProductsVO?
    {
        for(i in 0..categories.size-1)
        {
            if(categories.get(i).products.size>0)
            {
                for(j in 0.. categories.get(i).products.size-1) {
                    if (id == categories.get(i).products.get(j).id)
                    {
                        var productsVO = categories.get(i).products.get(j)
                        productsVO.count = rank
                        Log.i("Rank", "Id: "+id+ " || Rank: "+ productsVO.count)
                        return productsVO;
                    }
                }
            }
        }
        return null
    }

    private fun setRvAdapterView() {
        // set up the RecyclerView
        bindingLayout.includeContent.rvRanking.layoutManager = LinearLayoutManager(this@MainActivity)
        val adapter = RankingsAdapter(this@MainActivity, object :  RankingsAdapter.IProduct
        {
            override fun onItemClick(position: Int, rank:RankingsVO)
            {
                var list = hashMapDataView.get(rank.ranking)
                val intents = Intent(this@MainActivity, ProductsActivity:: class.java)
                intents.putParcelableArrayListExtra(Constants.KEY_DATA, list)
                intents.putExtra(Constants.KEY_TITLE, rank.ranking)
                intents.putExtra(Constants.KEY_RANKING, true)
                startActivity(intents)
            }}, rankings)
        bindingLayout.includeContent.rvRanking.adapter = adapter
    }
}
