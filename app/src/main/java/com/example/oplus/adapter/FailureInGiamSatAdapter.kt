package com.example.oplus.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.ScreenIDEnum
import com.example.oplus.model.failure.ResultDayWork
import kotlinx.android.synthetic.main.row_failure_giamsat.view.*

class FailureInGiamSatAdapter(var listWork: MutableList<ResultDayWork>) :
    RecyclerView.Adapter<FailureInGiamSatAdapter.ViewHolder>() {
    var onClick: ((ResultDayWork?) -> (Unit))? = null
    var type = ""
    fun insertData(listWork: MutableList<ResultDayWork>) {
        this.listWork = listWork
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_failure_giamsat, parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listWork.getOrNull(position)
        if (item != null) {
            holder.binding(item)
        }
    }

    override fun getItemCount() = listWork.size
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun binding(item:ResultDayWork){
            itemView.apply {
                Glide.with(ivFailureGiamsat.context).load(item.icon).into(ivFailureGiamsat)
                tvTimeGiamsat.text = item.thoiGianBatDau ?: ""
                tvTitleFailureGiamsat.text = item.title
                tvTitleFailureGiamsat.setTextColor(Color.parseColor(item.mauTieuDe))
                tvTitleCodeGiamsat.text = item.ma?.title
                tvValueCodeGiamsat.text = item.ma?.value
                tvTypeTitleGiamsat.text = item.ten?.title
                tvTypeValueGiamsat.text= item.ten?.value
                tvTitleStatusGiamsat.text = item.trangThai?.title
                tvValueStatusGiamsat.text = item.trangThai?.value
                tvValueStatusGiamsat.setTextColor(Color.parseColor(item.mauTrangThai))
            }
        }
       init {
           itemView.setOnClickListener {
               val position = adapterPosition
               val item = listWork[position]
               onClick?.invoke(item)
           }
       }
    }
}