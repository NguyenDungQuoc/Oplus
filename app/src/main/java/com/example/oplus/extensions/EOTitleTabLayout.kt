package com.example.oplus.extensions

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.example.oplus.R
import com.example.oplus.model.inventory.ResultTask

class EOTitleTabLayout(context: Context, attrs: AttributeSet?) : EOCustomTabLayout(context, attrs) {

    val selectedItem: ResultTask
        get() {
            return listHeader.get(selectedTabPosition)
        }
    var listHeader: MutableList<ResultTask> = mutableListOf()
        set(value) {
            field = value
            refreshTabItem()
        }

    private fun refreshTabItem() {
        removeAllTabs()
        listHeader.forEachIndexed { index, item ->
            addTab(getTabView(item, index))
        }
    }

    private fun getTabView(resultTask: ResultTask, index: Int): Tab {
        val tab = getTabAt(index) ?: newTab()
        val binding =  LayoutInflater.from(context).inflate(
                R.layout.row_button_task,
                null
            )
        binding.findViewById<TextView>(R.id.tvBadgeConfirm).text = resultTask.soLuong.toString()
        binding.findViewById<TextView>(R.id.tvTitleStatus).text = resultTask.title?.toUpperCase()
        tab.customView = binding
        return tab
    }
}