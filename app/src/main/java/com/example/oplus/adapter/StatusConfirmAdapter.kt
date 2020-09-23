package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oplus.R
import com.example.oplus.model.StatusConfirmInventory
import kotlinx.android.synthetic.main.row_button_confirm.view.*

class StatusConfirmAdapter(_listStatus: MutableList<StatusConfirmInventory>) :
    RecyclerView.Adapter<StatusConfirmAdapter.ViewHolder>() {
    var selectedPosition:Int = 0
    private var listStatus: MutableList<StatusConfirmInventory>? = _listStatus
    var parentWidth = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onClick: ((StatusConfirmInventory?) -> (Unit))? = null
    fun setData(listStatus: MutableList<StatusConfirmInventory>?) {
        this.listStatus = listStatus
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_button_confirm, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val status = listStatus?.getOrNull(position)
        if (parentWidth != 0) {
            holder.itemView.layoutParams.width = parentWidth / 2
            holder.itemView.requestLayout()
        }
        holder.itemView.apply {
            tvBadgeConfirm.text = status?.soLuong.toString()
            tvTitleStatus.text = status?.title?.toUpperCase()
            ctConfirm.isSelected = status?.isCheck ?: false
            tvTitleStatus.isSelected = status?.isCheck ?: false

        }
    }

    override fun getItemCount() = listStatus?.size ?: 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = listStatus?.getOrNull(position)
                val oldItem = listStatus?.get(selectedPosition)
                item?.isCheck = !(item?.isCheck ?: false)
                if(selectedPosition != position){
                    oldItem?.isCheck = false
                }else{
                    oldItem?.isCheck = true
                }
                selectedPosition = position
                onClick?.invoke(item)

                notifyDataSetChanged()
            }
        }
    }
}