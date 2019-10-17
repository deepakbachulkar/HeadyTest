package com.deepak.headytest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.deepak.headytest.R
import com.deepak.headytest.model.ProductsVO

class ProductsAdapter(private val context: Context, private val rupee: String, private val iProduct: ICategory, private var products: ArrayList<ProductsVO>) :
    RecyclerView.Adapter<ProductsAdapter.CategoryHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.tvProductName.text = products[position].name
        holder.tvPrice.text = rupee + products[position].variants.get(0).price.toString()
    }

    interface ICategory {
        fun onItemClick(position: Int)
    }


    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        var rootView=itemView
        var tvProductName: TextView
        var tvPrice: TextView

        init {
            tvProductName = itemView.findViewById(R.id.tv_product_name)
            tvPrice = itemView.findViewById(R.id.tv_product_price)
        }
        override fun onClick(view: View) {
            when (view.id) {
            }
        }


    }
}