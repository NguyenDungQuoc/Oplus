package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.failure.MediaDTO
import com.example.oplus.model.failure.ResultDayWork
import kotlinx.android.synthetic.main.row_img_attach.view.*

class ImgAttachAdapter(var listImg: MutableList<MediaDTO>) :
    RecyclerView.Adapter<ImgAttachAdapter.ViewHolder>() {

    var onClick: ((MediaDTO?) -> (Unit))? = null
    fun insertData(listImg: MutableList<MediaDTO>) {
        this.listImg = listImg
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_img_attach,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listImg.getOrNull(position)
        if (item != null) {
            holder.bingding(item)
        }
    }

    override fun getItemCount() = listImg.size

    inner class ViewHolder(var item: View) : RecyclerView.ViewHolder(item) {
        fun bingding(img:MediaDTO){
            Glide.with(item.imgDinhKem).load(img.fullUrl).into(item.imgDinhKem)
        }
        init {
            item.setOnClickListener {
                val position = adapterPosition
                val item = listImg.get(position)
                onClick?.invoke(item)
            }
        }
    }
}