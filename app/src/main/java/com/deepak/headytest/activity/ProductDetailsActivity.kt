package com.deepak.headytest.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.deepak.headytest.R
import com.deepak.headytest.Utils.Constants
import com.deepak.headytest.adapter.ProductVarientAdapter
import com.deepak.headytest.databinding.ActivityProductDetailsBinding
import com.deepak.headytest.model.ProductsVO
import com.deepak.headytest.model.VariantsVO
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : AppCompatActivity()
{
    lateinit var bindingLayout: ActivityProductDetailsBinding
    private var product = ProductsVO()
    private var selectedVariant:VariantsVO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setSupportActionBar(toolbarDetails)
        init()
    }

    fun init(){
        bindingLayout = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_product_details) as ActivityProductDetailsBinding

        if(intent.hasExtra(Constants.KEY_DATA)){
            product = intent.getParcelableExtra(Constants.KEY_DATA)
            initValue()
            bindingLayout.toolbarDetails.title = product.name
        }


        bindingLayout.btnAddToCart.setOnClickListener {
            if(selectedVariant!=null){
                Toast.makeText(this@ProductDetailsActivity, resources.getString(R.string.added_to_cart), Toast.LENGTH_SHORT).show()
            }
        }

        bindingLayout.toolbarDetails.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        bindingLayout.toolbarDetails.setNavigationOnClickListener(View.OnClickListener { finish() })
    }

    fun initValue()
    {
        if(product.variants.get(0).size.isEmpty())
            bindingLayout.tvSize.visibility = View.INVISIBLE
        else
            bindingLayout.tvSize.visibility = View.VISIBLE
        setRvAdapter()
    }

    private fun setRvAdapter()
    {
        // set up the RecyclerView
        bindingLayout.rcvVariant.layoutManager = LinearLayoutManager(this@ProductDetailsActivity)
        val adapter = ProductVarientAdapter(this@ProductDetailsActivity, resources.getString(R.string.rupees), object :   ProductVarientAdapter.IVatiants
        {
            override fun onItemClick(position: Int) {
                bindingLayout.btnAddToCart.isEnabled = true
                selectedVariant = product.variants.get(position)
                bindingLayout.llSelected.visibility = View.VISIBLE
                bindingLayout.tvSelectedColor.text = resources.getString(R.string.color) + ":  " + product.variants.get(position).color
                if(!product.variants.get(position).size.isEmpty()) {
                    bindingLayout.tvSelectedSize.visibility = View.VISIBLE
                    bindingLayout.tvSelectedSize.text =
                        resources.getString(R.string.size) + ":  " + product.variants.get(position).size
                }else{
                    bindingLayout.tvSelectedSize.visibility = View.GONE
                }
                bindingLayout.tvSelectedPrice.text =  resources.getString(R.string.price) + ":  " +resources.getString(R.string.rupees) + product.variants.get(position).price
            }}, product.variants)
        bindingLayout.rcvVariant.adapter = adapter
    }
}
