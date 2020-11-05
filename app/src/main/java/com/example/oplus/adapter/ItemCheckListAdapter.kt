package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.crop.ItemCheckList
import com.example.oplus.model.failure.ResultDayWork
import kotlinx.android.synthetic.main.row_item_checklist.view.*

class ItemCheckListAdapter(var listItem: MutableList<ItemCheckList>) :
    RecyclerView.Adapter<ItemCheckListAdapter.ViewHolder>() {
    var onClick: ((ItemCheckList?) -> (Unit))? = null

    fun insertData(listItem: MutableList<ItemCheckList>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_checklist, parent, false)
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
        fun binding(item: ItemCheckList) {
            itemView.apply {
                tvNumberSTT.text = item.sTT.toString()
                ckCheck.isChecked = item.check
                Glide.with(imgItemDSVT.context).load(item.icon)
                    .error(R.drawable.img_placeholder).into(imgItemDSVT)
                tvNameItemDSVT.text = item.title
                tvTitleMaDSVT.text = item.ma?.title
                tvValueMaDSVT.text = item.ma?.value
                tvTitleSLT.text = item.soLuongTon?.title
                tvValueSLT.text = item.soLuongTon?.value
                tvDL.text = item.dinhLuong
            }
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = listItem.get(position)
                onClick?.invoke(item)
            }
        }
    }
}