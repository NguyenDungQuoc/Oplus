package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.giamsat.ResultItemForArea
import kotlinx.android.synthetic.main.row_item_area_large.view.*
import kotlinx.android.synthetic.main.row_item_area_small.view.*

class ListItemForAreaAdapter(_listItem: MutableList<ResultItemForArea>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_WIDTH_LARGE = 0
        private const val TYPE_WIDTH_SMALL = 1
    }

    var parentWidth = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setData(list: MutableList<ResultItemForArea>) {
        this.listItem = list
        notifyDataSetChanged()
    }

    private var listItem: MutableList<ResultItemForArea> = _listItem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_WIDTH_LARGE) {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_area_large, parent, false)
            return ViewHolderWithLargeWidth(itemView)
        } else {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_item_area_small, parent, false)
            return ViewHolderWithSmallWidth(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = listItem.getOrNull(position)
        holder.apply {
            if (holder is ViewHolderWithLargeWidth){
                if (item != null) {
                    holder.setImage(item)
                }
            }else{
                if (item != null) {
                    (holder as ViewHolderWithSmallWidth).setImage(item)
                }
            }
        }
    }

    override fun getItemCount() = listItem.size

    override fun getItemViewType(position: Int): Int {
        val soDu = position % 5
        if (soDu % 2 == 0) {
            return TYPE_WIDTH_SMALL
        } else {
            return TYPE_WIDTH_LARGE
        }
    }

    inner class ViewHolderWithLargeWidth(var a: View) : RecyclerView.ViewHolder(a) {
        fun setImage(item:ResultItemForArea) {
            a.apply {

                if (item.isError == true) {
                    Glide.with(ivItemAreaLarge.context).load(item.errorIcon).into(ivItemAreaLarge)
                } else {
                    Glide.with(ivItemAreaLarge.context).load(item.icon).into(ivItemAreaLarge)
                }
                if(item.icon == null && item.errorIcon == null){
                    rootLarge.visibility = View.GONE
                }
            }
        }
    }

    inner class ViewHolderWithSmallWidth(var a: View) : RecyclerView.ViewHolder(a) {
        fun setImage(item:ResultItemForArea) {
            a.apply {
                if (item.isError == true) {
                    Glide.with(ivItemAreaSmall.context).load(item.errorIcon).into(ivItemAreaSmall)
                } else {
                    Glide.with(ivItemAreaSmall.context).load(item.icon).into(ivItemAreaSmall)
                }
            }
        }
    }
}