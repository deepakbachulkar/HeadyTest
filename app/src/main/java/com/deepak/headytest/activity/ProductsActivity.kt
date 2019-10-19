package com.deepak.headytest.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.deepak.headytest.R
import com.deepak.headytest.Utils.Constants
import com.deepak.headytest.adapter.ProductVarientAdapter
import com.deepak.headytest.adapter.ProductsAdapter
import com.deepak.headytest.model.ProductsVO
import com.deepak.headytest.model.VariantsVO

import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class ProductsActivity : AppCompatActivity()
{
    private var products = ArrayList<ProductsVO>()
    private lateinit var rcvVarients:RecyclerView
    private var isRanking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        init()
        setRvAdapter()
    }

    fun init(){
        rcvVarients = findViewById(R.id.rcv_list)

        if(intent.hasExtra(Constants.KEY_DATA)){
            products = intent.getParcelableArrayListExtra(Constants.KEY_DATA)
        }

        if(intent.hasExtra(Constants.KEY_TITLE)){
            this.title = intent.getStringExtra(Constants.KEY_TITLE)
        }


        if(intent.hasExtra(Constants.KEY_RANKING)){
            isRanking = intent.getBooleanExtra(Constants.KEY_RANKING, false)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
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

    private fun setRvAdapter()
    {
        // set up the RecyclerView
        rcvVarients.layoutManager = LinearLayoutManager(this@ProductsActivity)
        val adapter = ProductsAdapter(this@ProductsActivity, resources.getString(R.string.rupees), isRanking, object :  ProductsAdapter.ICategory {
            override fun onItemClick(position: Int) {
                val intents = Intent(this@ProductsActivity, ProductDetailsActivity:: class.java)
                intents.putExtra(Constants.KEY_DATA, products.get(position))
                intents.putExtra(Constants.KEY_RANKING, isRanking)
                startActivity(intents)
            }}, products )
        rcvVarients.adapter = adapter
    }
}
