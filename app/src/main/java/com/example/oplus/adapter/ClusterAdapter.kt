package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oplus.R
import com.example.oplus.model.crop.ClusterDTO
import kotlinx.android.synthetic.main.row_list_tablayout.view.*

class ClusterAdapter(var list: MutableList<ClusterDTO>) : RecyclerView.Adapter<ClusterAdapter.ViewHolder>() {
    var selectedPosition:Int = 0
    var onClick: ((ClusterDTO?) -> (Unit))? = null
    fun bindingData(list: MutableList<ClusterDTO>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_list_tablayout, parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.getOrNull(position)
        if (item != null) {
            holder.binding(item)
        }
    }

    override fun getItemCount()= list.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun binding(item:ClusterDTO){
            itemView.apply {
                tvTitleList.text = item.title
                if(item.isSelected){
                    imgCheck.visibility = View.VISIBLE
                    ctListCluster.isSelected = item.isSelected
                }else{
                    imgCheck.visibility = View.GONE
                    ctListCluster.isSelected = item.isSelected
                }
            }
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = list.get(position)
                onClick?.invoke(item)
                val oldItem = list?.get(selectedPosition)
                if(selectedPosition != position){
                    oldItem.isSelected = false
                }else{
                    oldItem.isSelected = true
                }
                selectedPosition = position
                notifyDataSetChanged()
            }
        }
    }
}