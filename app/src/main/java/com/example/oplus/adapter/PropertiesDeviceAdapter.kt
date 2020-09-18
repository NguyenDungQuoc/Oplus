package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oplus.R
import com.example.oplus.model.Properties
import kotlinx.android.synthetic.main.row_properties_divice.view.*

class PropertiesDeviceAdapter(_listProperties: MutableList<Properties>) :
    RecyclerView.Adapter<PropertiesDeviceAdapter.ViewHolder>() {
    private var listProperties: MutableList<Properties> = _listProperties
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setData(listProperties: MutableList<Properties>) {
        this.listProperties = listProperties
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_properties_divice, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listProperties.getOrNull(position)
        holder.itemView.apply {
            tvTitle.text = list?.Title
            tvValue.text = list?.Value
        }
    }

    override fun getItemCount() = listProperties.size

}