package com.deepak.headytest.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.deepak.headytest.R
import com.deepak.headytest.Utils.Constants
import com.deepak.headytest.adapter.ProductsAdapter
import com.deepak.headytest.model.ProductsVO
import java.util.*
import kotlin.collections.ArrayList


class ProductsActivity : AppCompatActivity()
{
    private var products = ArrayList<ProductsVO>()
    private var isRanking = false
    private var title = ""
    lateinit var bindingLayout: com.deepak.headytest.databinding.ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setRvAdapter()
    }

    fun init(){
        bindingLayout = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main) as com.deepak.headytest.databinding.ActivityMainBinding
        if(isRanking)
            setSupportActionBar(bindingLayout.toolbar)
        bindingLayout.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        bindingLayout.toolbar.setNavigationOnClickListener(View.OnClickListener { finish() })

        bindingLayout.includeContent.progressBar.visibility = View.GONE
        if(intent.hasExtra(Constants.KEY_DATA)){
            products = intent.getParcelableArrayListExtra(Constants.KEY_DATA)
        }

        if(intent.hasExtra(Constants.KEY_TITLE)){
            title = intent.getStringExtra(Constants.KEY_TITLE)
            bindingLayout.toolbar.title = title.toUpperCase()
        }

        if(intent.hasExtra(Constants.KEY_RANKING)){
            isRanking = intent.getBooleanExtra(Constants.KEY_RANKING, false)
        }
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
            R.id.action_by_descending ->
            {
                Collections.sort<ProductsVO>(products) { productVO, productVO1 ->
                    productVO.count.compareTo(productVO1.count)
                }
                Collections.reverse(products)
                adapter!!.notifyDataSetChanged()
                return  true
            }
            R.id.action_by_ascending -> {
              Collections.sort<ProductsVO>(products) { productVO, productVO1 ->
                    productVO.count.compareTo(productVO1.count)
                }
                adapter!!.notifyDataSetChanged()
               return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    var adapter :ProductsAdapter ? = null

    private fun setRvAdapter()
    {
        // set up the RecyclerView
        bindingLayout.includeContent.rvCategories.layoutManager = LinearLayoutManager(this@ProductsActivity)
         adapter = ProductsAdapter(this@ProductsActivity, resources.getString(R.string.rupees), isRanking, object :  ProductsAdapter.ICategory {
            override fun onItemClick(position: Int) {
                val intents = Intent(this@ProductsActivity, ProductDetailsActivity:: class.java)
                intents.putExtra(Constants.KEY_DATA, products.get(position))
                intents.putExtra(Constants.KEY_RANKING, isRanking)
                intents.putExtra(Constants.KEY_TITLE, title)
                startActivity(intents)
            }}, products )
        bindingLayout.includeContent.rvCategories.adapter = adapter
    }


}
