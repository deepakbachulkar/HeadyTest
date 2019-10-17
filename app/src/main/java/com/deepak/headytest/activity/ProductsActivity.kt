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

class ProductsActivity : AppCompatActivity()
{
    private var products = ArrayList<ProductsVO>()
    private lateinit var rcvProducts:RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        init()
        setRvAdapter()
    }

    fun init(){
        rcvProducts = findViewById(R.id.rcv_list)
        progressBar =  findViewById(R.id.progress_bar)

        if(intent.hasExtra("data")){
            products = intent.getParcelableArrayListExtra("data")

        }
        if(intent.hasExtra("title")){
            this.setTitle(intent.getStringExtra("title"))
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
        progressBar.visibility = View.GONE
        // set up the RecyclerView
        rcvProducts.layoutManager = LinearLayoutManager(this@ProductsActivity)
        val adapter = ProductsAdapter(this@ProductsActivity, resources.getString(R.string.rupees), object :  ProductsAdapter.ICategory {
            override fun onItemClick(position: Int) {

            }}, products)
                val verticalDecoration = DividerItemDecoration(this@ProductsActivity, DividerItemDecoration.HORIZONTAL)
        verticalDecoration.setDrawable(ContextCompat.getDrawable(this@ProductsActivity!!,
            R.drawable.line
        )!!)
        rcvProducts.addItemDecoration(verticalDecoration)
        rcvProducts.adapter = adapter
    }
}
