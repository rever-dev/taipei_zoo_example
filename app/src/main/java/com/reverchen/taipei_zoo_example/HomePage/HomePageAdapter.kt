package com.reverchen.taipei_zoo_example.HomePage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reverchen.taipei_zoo_example.DataBean.Garden
import com.reverchen.taipei_zoo_example.R
import com.squareup.picasso.Picasso
import dev.weiqi.resof.stringOf

class HomePageAdapter : ListAdapter<Garden, HomePageAdapter.ViewHolder>(DiffCallback()) {
    
    var callback: ItemClickCallback? = null
    
    interface ItemClickCallback {
        
        fun onItemClick(_id: Int)
    }
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.itemGar_img)
        val tvTitle = itemView.findViewById<TextView>(R.id.itemGar_tvTitle)
        val tvInfo = itemView.findViewById<TextView>(R.id.itemGar_tvInfo)
        val tvMemo = itemView.findViewById<TextView>(R.id.itemGar_tvMemo)
        
        companion object {
            fun createView(parent: ViewGroup): ViewHolder {
                return ViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_garden, parent, false)
                )
            }
        }
        
        fun bindView(data: Garden) {
            Picasso.get()
                .load(data.E_Pic_URL)
                .fit()
                .centerCrop()
                .into(img)
            
            tvTitle.text = data.E_Name
            tvInfo.text = data.E_Info
            
            val memoStr =
                if (data.E_Memo == null || data.E_Memo.isNullOrEmpty())
                    stringOf(R.string.noMemoData)
                else
                    data.E_Memo
            tvMemo.text = memoStr
        }
    }
    
    class DiffCallback : DiffUtil.ItemCallback<Garden>() {
        override fun areItemsTheSame(oldItem: Garden, newItem: Garden): Boolean {
            return true // TODO:
        }
        
        override fun areContentsTheSame(oldItem: Garden, newItem: Garden): Boolean {
            return false // TODO:
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.createView(parent)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(currentList[position])
        holder.itemView.setOnClickListener {
            callback?.onItemClick(currentList[position]._id!!)
        }
    }
}