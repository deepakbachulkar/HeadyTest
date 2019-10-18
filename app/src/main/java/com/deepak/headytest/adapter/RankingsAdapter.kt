package com.deepak.headytest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.deepak.headytest.R
import com.deepak.headytest.model.RankingsVO

class RankingsAdapter(private val context: Context, private val iProduct: IProduct,
                      private var ranking: ArrayList<RankingsVO>) :
    RecyclerView.Adapter<RankingsAdapter.RankingHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingHolder {
        return RankingHolder(LayoutInflater.from(context).inflate(R.layout.item_ranking, parent, false))
    }

    override fun getItemCount(): Int {
        return ranking.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RankingHolder, position: Int) {
        holder.tvRanking.text = ranking.get(position).ranking
        holder.relLayout.setOnClickListener {
            iProduct.onItemClick(position, ranking.get(position))
        }
    }

    interface IProduct {
        fun onItemClick(position: Int, rank:RankingsVO)
    }


    class RankingHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener
    {
        var relLayout = itemView
        var tvRanking: TextView

        init {
            relLayout = itemView.findViewById(R.id.rl)
            tvRanking = itemView.findViewById(R.id.tv_ranking)
        }
        override fun onClick(view: View) {
            when (view.id) {
            }
        }


    }
}