package com.example.oplus.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class TabAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val mFragmentList: ArrayList<Fragment> = ArrayList()
    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment?) {
        if (fragment != null) {
            mFragmentList.add(fragment)
        }
    }
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }
}