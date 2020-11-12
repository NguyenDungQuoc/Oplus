package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oplus.R
import com.example.oplus.model.general.StatusValue
import kotlinx.android.synthetic.main.row_properties_bug.view.*


class BugsPropertiesAdapter(var list: MutableList<StatusValue>) : RecyclerView.Adapter<BugsPropertiesAdapter.ViewHolder>() {

    fun bindingData(list: MutableList<StatusValue>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_properties_bug, parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.getOrNull(position)
        if (item != null) {
            holder.binding(item)
        }
    }

    override fun getItemCount()= list.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun binding(item:StatusValue){
            itemView.apply {
                tvTitleProperties.text = item.title
                tvValueProperties.text = item.value ?: ""
            }
        }
    }
}