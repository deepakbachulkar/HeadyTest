package com.deepak.headytest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import com.deepak.headytest.R
import com.deepak.headytest.model.VariantsVO

class ProductVarientAdapter(private val context: Context, private val rupee: String, private val iVariant: IVatiants, private var varients: ArrayList<VariantsVO>) :
    RecyclerView.Adapter<ProductVarientAdapter.CategoryHolder>()
{
    var indexSelected = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        return CategoryHolder(LayoutInflater.from(context).inflate(R.layout.item_product_details, parent, false))
    }

    override fun getItemCount(): Int {
        return varients.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.tvColor.text = varients[position].color
        holder.tvSize.text = varients[position].size
        holder.tvPrice.text = rupee + varients[position].price
        holder.rootView.setOnClickListener {
            indexSelected = position
            iVariant.onItemClick(position)
            notifyDataSetChanged()
        }
        if (indexSelected == position)
            holder.rb.isChecked = true
        else
            holder.rb.isChecked = false
    }
    interface IVatiants {
        fun onItemClick(position: Int)

    }

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var rootView = itemView
        var tvSize: TextView
        var tvColor: TextView
        var tvPrice: TextView
        var rb:RadioButton
        init {
            tvSize = itemView.findViewById(R.id.tv_size)
            tvColor = itemView.findViewById(R.id.tv_color)
            tvPrice = itemView.findViewById(R.id.tv_price)
            rb = itemView.findViewById(R.id.rb)
        }
    }
}