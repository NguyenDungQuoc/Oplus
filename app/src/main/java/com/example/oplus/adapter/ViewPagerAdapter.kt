package com.example.oplus.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class ViewPagerAdapter(fm: FragmentManager, val listName:MutableList<Fragment>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var type = ""
    override fun getCount(): Int {
        return listName.size
    }

    override fun getItem(position: Int): Fragment {
        return  listName.get(position)

    }
}