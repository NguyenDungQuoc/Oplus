package com.example.oplus.fragment.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.oplus.activities.BaseActivity
import com.example.oplus.viewmodel.BaseTaskViewModel
import com.example.oplus.viewmodel.BaseViewModel

abstract class BaseFragment(resource:Int) : Fragment(resource) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        getViewModel().errorMessage?.observe(viewLifecycleOwner,{
            (activity as BaseActivity).showDialogCustom(1,it)
        })
    }

    abstract fun getViewModel(): BaseViewModel

    open fun initView() {

    }
    fun showLoading(){
        (activity as BaseActivity).showLoading()
    }
    fun hideLoading(){
        (activity as BaseActivity).hideLoading()
    }
}