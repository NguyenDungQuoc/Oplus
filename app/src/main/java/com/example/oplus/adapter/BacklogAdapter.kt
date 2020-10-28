package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oplus.R
import com.example.oplus.model.failure.ResultBacklogDTO
import kotlinx.android.synthetic.main.row_backlog.view.*

class BacklogAdapter(var listBacklog: MutableList<ResultBacklogDTO>) :
    RecyclerView.Adapter<BacklogAdapter.ViewHolder>() {
    var dayWorkAdapter:DayWorkAdapter? = null
    fun insertData(listBacklog: MutableList<ResultBacklogDTO>){
        this.listBacklog = listBacklog
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_backlog, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemView = listBacklog.getOrNull(position)
        if (itemView != null) {
            holder.binding(itemView)
        }
    }

    override fun getItemCount() = listBacklog.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun binding(backlog: ResultBacklogDTO) {
            itemView.apply {
                tvDate.text = backlog.date
                dayWorkAdapter = DayWorkAdapter(mutableListOf())
                rvChildBacklog.layoutManager = LinearLayoutManager(itemView.context)
                rvChildBacklog.setHasFixedSize(true)
                backlog.items?.let { dayWorkAdapter?.insertData(it) }
                rvChildBacklog.adapter = dayWorkAdapter
            }

        }
    }
}