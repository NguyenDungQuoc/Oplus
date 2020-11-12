package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.ItemResultMenu
import com.example.oplus.model.giamsat.GiamSatItem
import kotlinx.android.synthetic.main.row_giamsat.view.*
import java.util.*

class ItemGiamSatAdapter(_item: MutableList<GiamSatItem>) :
    RecyclerView.Adapter<ItemGiamSatAdapter.ViewHolder>() {
    private var listItem: MutableList<GiamSatItem> = _item
    var onClick: ((GiamSatItem?) -> (Unit))? = null
    var parentWidth = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setData(list: MutableList<GiamSatItem>) {
        this.listItem = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_giamsat, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem.getOrNull(position)
        if (parentWidth != 0) {
            holder.itemView.layoutParams.height = parentWidth / 2
            holder.itemView.requestLayout()
        }
        holder.itemView.apply {
            Glide.with(ivGiamSat.context).load(item?.thumbnail).error(R.drawable.empty)
                .into(ivGiamSat)
            tvTitleItem.text = item?.title?.toUpperCase(Locale.ROOT)
            if(item?.tasks?.toInt() == 0){
                tvTask.visibility = View.GONE
            }else{
                tvTask.visibility = View.VISIBLE
                tvTask.text = item?.tasks
                cvRow.setCardBackgroundColor(resources.getColor(R.color.red))
                tvTitleItem.setTextColor(resources.getColor(R.color.white))
            }
        }
    }

    override fun getItemCount() = listItem.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = listItem.getOrNull(position)
                onClick?.invoke(item)
            }
        }
    }
}