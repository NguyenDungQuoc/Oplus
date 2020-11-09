package com.example.oplus.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.activities.BugsDetailActivity
import com.example.oplus.model.crop.VatTuDTO
import kotlinx.android.synthetic.main.row_bugs.view.*

class BugsAdapter(var list: MutableList<VatTuDTO>) : RecyclerView.Adapter<BugsAdapter.ViewHolder>() {
    var onClick: ((VatTuDTO?) -> (Unit))? = null

    fun bindingData(list: MutableList<VatTuDTO>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_bugs, parent,false)
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
        fun binding(item:VatTuDTO){
            itemView.apply {
                Glide.with(imgBug.context).load(item.icon).error(R.drawable.img_placeholder).into(imgBug)
                tvNameBug.text = item.title
                tvGroupBugTitle.text = item.properties?.get(0)?.title
                tvGroupBugValue.text = item.properties?.get(0)?.value
                tvBoPhanTitle.text = item.properties?.get(1)?.title
                tvBoPhanValue.text = item.properties?.get(1)?.value
                tvXemCT.setOnClickListener {
                    val intent = Intent(context,BugsDetailActivity::class.java)
                    intent.putExtra("ID_ITEM",item.iD)
                    context.startActivity(intent)
                }
                itemView.setOnClickListener {
                    val position = adapterPosition
                    val itemClick = list.get(position)
                    onClick?.invoke(itemClick)
                    notifyDataSetChanged()
                }
            }
        }
    }
}