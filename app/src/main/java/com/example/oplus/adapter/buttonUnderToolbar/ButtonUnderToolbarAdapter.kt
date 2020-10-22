package com.example.oplus.adapter.buttonUnderToolbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oplus.R
import com.example.oplus.model.inventory.ResultTask
import kotlinx.android.synthetic.main.row_button_confirm.view.*

class ButtonUnderToolbarAdapter(_listStatuses: MutableList<ResultTask>) :
    RecyclerView.Adapter<ButtonUnderToolbarAdapter.ViewHolder>() {
    var selectedPosition:Int = 0
    private var listStatuses: MutableList<ResultTask>? = _listStatuses
    var parentWidth = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onClick: ((ResultTask?) -> (Unit))? = null
    fun setData(listStatuses: MutableList<ResultTask>?) {
        this.listStatuses = listStatuses
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_button_confirm, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val status = listStatuses?.getOrNull(position)
        if (parentWidth != 0) {
            holder.itemView.layoutParams.width = parentWidth / listStatuses?.size!!
            holder.itemView.requestLayout()
        }
        holder.itemView.apply {
            tvBadgeConfirm.text = status?.soLuong.toString()
            tvTitleStatus.text = status?.title?.toUpperCase()
            ctConfirm.isSelected = status?.isCheck ?: false
            tvTitleStatus.isSelected = status?.isCheck ?: false

        }
    }

    override fun getItemCount() = listStatuses?.size ?: 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = listStatuses?.getOrNull(position)
                val oldItem = listStatuses?.get(selectedPosition)
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