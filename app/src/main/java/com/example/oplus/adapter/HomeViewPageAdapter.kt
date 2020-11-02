package com.example.oplus.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.oplus.fragment.DashBoardMenuFragment
import com.example.oplus.model.ItemResultMenu
import kotlin.math.min

class HomeViewPageAdapter(
    var list: MutableList<ItemResultMenu>,
    fm: FragmentManager,
    private var countPage: Int
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var numberItemInPager = 8
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    fun insertData(list: MutableList<ItemResultMenu>, countOfPage: Int) {
        this.list = list
        countPage = countOfPage
        notifyDataSetChanged()
    }

    override fun getCount() = countPage

    override fun getItem(position: Int): Fragment {
        val min = min(position * numberItemInPager + numberItemInPager, list.size)
        val b: MutableList<ItemResultMenu> = mutableListOf()
        b.addAll(list.subList((position * numberItemInPager), min))
        return DashBoardMenuFragment.newInstance(b)

    }
}

enum class TypePager(var value: Int) {
    HOME(0), DASHBOARD(1)
}