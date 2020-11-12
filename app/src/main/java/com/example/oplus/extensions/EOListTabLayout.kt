package com.example.oplus.extensions

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import com.example.oplus.R
import com.example.oplus.model.crop.ClusterDTO

class EOListTabLayout(context: Context, attrs: AttributeSet?) : EOCustomTabLayout(context, attrs) {

    val selectedItem: ClusterDTO
        get() {
            return listHeader.get(selectedTabPosition)
        }
    var listHeader: MutableList<ClusterDTO> = mutableListOf()
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

    private fun getTabView(clusterDTO: ClusterDTO, index: Int): Tab {
        val tab = getTabAt(index) ?: newTab()
        val binding =  LayoutInflater.from(context).inflate(
                R.layout.row_list_tablayout,
                null
            )
        binding.findViewById<TextView>(R.id.tvTitleList).text = clusterDTO.title
        tab.customView = binding
        return tab
    }
}