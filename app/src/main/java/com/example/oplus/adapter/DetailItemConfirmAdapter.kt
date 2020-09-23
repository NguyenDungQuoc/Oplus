package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oplus.R
import com.example.oplus.model.StatusInventory
import kotlinx.android.synthetic.main.row_properties_divice.view.*

class DetailItemConfirmAdapter(_listItem: MutableList<StatusInventory>) :
    RecyclerView.Adapter<DetailItemConfirmAdapter.ViewHolder>() {
    private var listItem:MutableList<StatusInventory> = _listItem


    fun setData(listItem:MutableList<StatusInventory>){
        this.listItem = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_properties_divice, parent, false)
        return  ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listItem.getOrNull(position)
        holder.itemView.apply {
            tvTitle.text = list?.title
            tvValue.text = list?.value ?: ""
        }
    }

    override fun getItemCount() = listItem.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}