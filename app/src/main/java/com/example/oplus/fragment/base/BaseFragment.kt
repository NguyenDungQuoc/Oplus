package com.example.oplus.fragment.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.oplus.activities.BaseActivity

abstract class BaseFragment(resource:Int) : Fragment(resource) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    open fun initView() {

    }
    fun showLoading(){
        (activity as BaseActivity).showLoading()
    }
    fun hideLoading(){
        (activity as BaseActivity).hideLoading()
    }
}