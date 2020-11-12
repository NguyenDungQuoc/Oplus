package com.example.oplus.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.ScreenIDEnum
import com.example.oplus.activities.CheckListActivity
import com.example.oplus.model.failure.ResultDayWork
import com.example.oplus.model.inventory.FarmDevice
import kotlinx.android.synthetic.main.row_failure.view.*

class DayWorkAdapter(var listWork: MutableList<ResultDayWork>) :
    RecyclerView.Adapter<DayWorkAdapter.ViewHolder>() {
    var onClick: ((ResultDayWork?) -> (Unit))? = null
    var type = ""
    fun insertData(listWork: MutableList<ResultDayWork>) {
        this.listWork = listWork
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_failure, parent,false)
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
                Glide.with(ivFailure.context).load(item.icon).into(ivFailure)
                tvTime.text = item.thoiGianBatDau ?: ""
                tvTitleFailure.text = item.title
                tvTitleFailure.setTextColor(Color.parseColor(item.mauTieuDe))
                tvTitleCode.text = item.ma?.title
                tvValueCode.text = item.ma?.value
                tvTitleStatus.text = item.trangThai?.title
                tvValueStatus.text = item.trangThai?.value
                tvValueStatus.setTextColor(Color.parseColor(item.mauTrangThai))
                if(type == ScreenIDEnum.FAILURE_SCREEN.value){
                    imgCheckList.visibility = View.GONE
                }
                imgCheckList.setOnClickListener {
                    val intent = Intent(context,CheckListActivity::class.java)
                    intent.putExtra("ID_ITEM",item.iD).putExtra("TITLE",item.title)
                    context.startActivity(intent)
                }
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