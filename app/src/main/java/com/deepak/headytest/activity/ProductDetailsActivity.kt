package com.deepak.headytest.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.deepak.headytest.R
import com.deepak.headytest.adapter.ProductsAdapter
import com.deepak.headytest.model.ProductsVO

import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class ProductDetailsActivity : AppCompatActivity()
{
    private var products = ProductsVO()
    private lateinit var rcvProducts:RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setSupportActionBar(toolbar)
        init()
    }

    fun init(){
        if(intent.hasExtra("data")){
            products = intent.getParcelableExtra("data")
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

}
