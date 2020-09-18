package com.example.oplus.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.oplus.ImgDeviceFragment
import com.example.oplus.model.ResultThuocTinhDong

class ViewpageImageDeviceAdater(
    fm: FragmentManager,
    private val device: MutableList<String>? = null
) : FragmentStatePagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int {
        return device?.size ?: 0
    }

    override fun getItem(position: Int): Fragment {
        return ImgDeviceFragment.newInstance(device?.getOrNull(position))
    }
}