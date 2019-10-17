package com.deepak.headytest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.deepak.headytest.R
import com.deepak.headytest.model.CategoryVO

class CategoryAdapter(private val context: Context, private val iCategory: ICategory, private var categories: ArrayList<CategoryVO>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(LayoutInflater.from(context).inflate(R.layout.item_catgories, parent, false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.tvCatgories.text = categories[position].name
        holder.tvCount.text = categories[position].products.size.toString()
        holder.rootView.setOnClickListener {
            iCategory.onItemClick(position)
        }
    }

    interface ICategory {
        fun onItemClick(position: Int)
    }


    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        var rootView=itemView
        var tvCatgories: TextView
        var tvCount: TextView

        init {
            tvCatgories = itemView.findViewById(R.id.tv_catgories)
            tvCount = itemView.findViewById(R.id.tv_product_count)
        }
        override fun onClick(view: View) {
            when (view.id) {
            }
        }


    }
}