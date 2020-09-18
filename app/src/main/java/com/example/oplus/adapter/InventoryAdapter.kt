package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.FarmDevice
import kotlinx.android.synthetic.main.row_status_inventory.view.*

class InventoryAdapter(_listTtem: MutableList<FarmDevice>) :
    RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {
    private var listItem: MutableList<FarmDevice> = _listTtem
    var onClick: ((FarmDevice?) -> (Unit))? = null
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = listItem[position]
                onClick?.invoke(item)
            }
        }
    }

    fun setData(listItem: MutableList<FarmDevice>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_status_inventory, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listDevice = listItem.getOrNull(position)
        holder.itemView.apply {
            Glide.with(imgItemInventory.context).load(listDevice?.Thumbnail).into(imgItemInventory)
            tvNameItemInventory.text = listDevice?.Title
            tvSoLuongTon.text = listDevice?.SoLuongTon?.Title
            tvNumSoLuongTon.text = listDevice?.SoLuongTon?.Value ?: "0"
            tvLoaiThietBi.text = listDevice?.Loai?.Title
            tvNumLoaiThietBi.text = listDevice?.Loai?.Value ?: "0"
            tvMa.text = listDevice?.Ma?.Title
            tvMaThietBi.text = listDevice?.Ma?.Value ?: "0"
        }
    }

    override fun getItemCount() = listItem.size
}