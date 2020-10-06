package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.giamsat.InfoItem
import kotlinx.android.synthetic.main.row_giamsat.view.*
import kotlinx.android.synthetic.main.row_giamsat.view.tvTitleItem
import kotlinx.android.synthetic.main.row_info_item.view.*

class ItemInfoAdapter(_listitem: MutableList<InfoItem>) :
    RecyclerView.Adapter<ItemInfoAdapter.ViewHolder>() {
    private var listItem: MutableList<InfoItem> = _listitem
    fun setData(list: MutableList<InfoItem>) {
        this.listItem = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_info_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = listItem.getOrNull(position)
        holder.itemView.apply {
            tvTitleItem.text = listItem?.title
            Glide.with(ivImageItem.context).load(listItem?.icon).into(ivImageItem)
        }
    }

    override fun getItemCount() = listItem.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}