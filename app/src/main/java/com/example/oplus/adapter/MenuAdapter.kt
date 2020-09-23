package com.example.oplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.ItemResultMenu
import kotlinx.android.synthetic.main.row_dashboard.view.*


class MenuAdapter(
    _menuDashBoard: MutableList<ItemResultMenu>
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    private var menuDashBoard: MutableList<ItemResultMenu> = _menuDashBoard
    var parentHeight = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onClick: ((ItemResultMenu?) -> (Unit))? = null

    fun setData(menuDashBoard: MutableList<ItemResultMenu>) {
        this.menuDashBoard = menuDashBoard
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val item = menuDashBoard[position]
                onClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_dashboard, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menuItem = menuDashBoard.getOrNull(position)
        if (parentHeight != 0) {
            holder.itemView.layoutParams.height = parentHeight / 4
            holder.itemView.requestLayout()
        }
        holder.itemView.apply {
            Glide.with(imgMenuDashBoard.context).load(menuItem?.imageUrl).into(imgMenuDashBoard)
            tvMenuDashBoard.text = menuItem?.title
            if (menuItem?.totalTask == 0) {
                tvBadge.visibility = View.GONE
            } else {
                tvBadge.text = menuItem?.totalTask.toString()
            }
        }
    }

    override fun getItemCount() = menuDashBoard.size


}