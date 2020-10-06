package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.inventory.FarmDevice
import kotlinx.android.synthetic.main.row_status_inventory.view.*

class InventoryAdapter(_listItem: MutableList<FarmDevice>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_LOAD_MORE = 0
        private const val TYPE_NORMAL = 1
    }

    private var listItem: MutableList<FarmDevice> = _listItem
    var onClick: ((FarmDevice?) -> (Unit))? = null
    var isLoading: Boolean = true


    fun insertData(listItem: MutableList<FarmDevice>) {
        if(listItem.size == 0){
            isLoading = false
        }
        this.listItem.addAll(listItem)
        notifyDataSetChanged()
    }
    fun setData(listItem: MutableList<FarmDevice>){
        this.listItem = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_LOAD_MORE) {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.loading_more_item, parent, false)
            return Loading(itemView)
        } else {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_status_inventory, parent, false)
            return ViewHolder(itemView)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val listDevice = listItem.getOrNull(position)
            holder.itemView.apply {
                Glide.with(imgItemInventory.context).load(listDevice?.thumbnail)
                    .into(imgItemInventory)
                tvNameItemInventory.text = listDevice?.title
                tvSoLuongTon.text = listDevice?.soLuongTon?.title
                tvNumSoLuongTon.text = listDevice?.soLuongTon?.value ?: "0"
                tvLoaiThietBi.text = listDevice?.loai?.title
                tvNumLoaiThietBi.text = listDevice?.loai?.value ?: "0"
                tvMa.text = listDevice?.ma?.title
                tvMaThietBi.text = listDevice?.ma?.value ?: "0"
            }
        }

    }

    fun clearData(){
        listItem.clear()
        isLoading = false
    }
    override fun getItemViewType(position: Int): Int {
        if (position == itemCount - 1 && isLoading) {
            return TYPE_LOAD_MORE
        } else
            return TYPE_NORMAL
    }


    override fun getItemCount(): Int {
        if (isLoading) {
            if (listItem.size == 0)
                return listItem.size
            return listItem.size + 1
        } else
            return listItem.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = listItem[position]
                onClick?.invoke(item)
            }
        }
    }

    inner class Loading(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}