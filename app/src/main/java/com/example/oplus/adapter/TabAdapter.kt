package com.example.oplus.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.oplus.fragment.failure.BacklogFragment
import com.example.oplus.fragment.failure.TaskFragment
import com.example.oplus.model.inventory.ResultTask


class TabAdapter(fm: FragmentManager,val listName:MutableList<ResultTask>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return listName.size
    }

//    fun addFragment(fragment: Fragment?) {
//        if (fragment != null) {
//            listName.add(fragment)
//        }
//    }
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                listName.get(0).tabName?.let { TaskFragment(it) } ?: Fragment()
            }
            1 -> {
                BacklogFragment()
            }
            2 -> {
                listName.get(2).tabName?.let { TaskFragment(it) } ?: Fragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}