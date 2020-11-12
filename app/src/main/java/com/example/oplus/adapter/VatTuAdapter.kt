package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.crop.VatTuDTO
import kotlinx.android.synthetic.main.row_item_checklist.view.*

class VatTuAdapter(var listItem: MutableList<VatTuDTO>) :
    RecyclerView.Adapter<VatTuAdapter.ViewHolder>() {

    fun insertData(listItem: MutableList<VatTuDTO>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_danhsach_vattu, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem.getOrNull(position)
        if (item != null) {
            holder.binding(item)
        }
    }

    override fun getItemCount() = listItem.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun binding(item: VatTuDTO) {
            itemView.apply {
                Glide.with(imgItemDSVT.context).load(item.icon).error(R.drawable.img_placeholder).into(imgItemDSVT)
                tvNameItemDSVT.text = item.title
                tvTitleMaDSVT.text = item.properties?.get(0)?.title
                tvValueMaDSVT.text = item.properties?.get(0)?.value
                tvTitleSLT.text = item.properties?.get(1)?.title
                tvValueSLT.text = item.properties?.get(1)?.value
            }
        }
    }
}