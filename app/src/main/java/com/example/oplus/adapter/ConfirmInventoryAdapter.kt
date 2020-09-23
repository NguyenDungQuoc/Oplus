package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.ItemConfirmInventory
import kotlinx.android.synthetic.main.row_item_confirm.view.*



class ConfirmInventoryAdapter(_listItem: MutableList<ItemConfirmInventory>) :
    RecyclerView.Adapter<ConfirmInventoryAdapter.ViewHolder>() {
    private var listItem: MutableList<ItemConfirmInventory> = _listItem
    var onClick: ((ItemConfirmInventory?) -> (Unit))? = null


    fun setData(listItem: MutableList<ItemConfirmInventory>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item_confirm, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listDevice = listItem.getOrNull(position)
        holder.itemView.apply {
            Glide.with(imgItemConfirm.context).load(listDevice?.thumbnail).into(imgItemConfirm)
            tvNameItemConfirm.text = listDevice?.title
            tvSoLuongMua.text = listDevice?.soLuongTon?.title
            tvNumSoLuongMua.text = listDevice?.soLuongTon?.value ?: "0"
            tvNgayMua.text = listDevice?.loai?.title
            tvNumNgayMua.text = listDevice?.loai?.value ?: "0"
            tvNgayNhan.text = listDevice?.ma?.title
            tvValueNgayNhan.text = listDevice?.ma?.value ?: "0"
            tvDonViTinh.text = listDevice?.donViTinh?.title
            tvValueDonViTinh.text = listDevice?.donViTinh?.value
        }
    }

    override fun getItemCount() = listItem.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = listItem[position]
                onClick?.invoke(item)
            }
        }
    }
}