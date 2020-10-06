package com.example.oplus.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.oplus.CustomProgressDialog
import com.example.oplus.R

abstract class BaseFragment(resource:Int) : Fragment(resource) {
    var loadingDialog: CustomProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        loadingDialog = activity?.let { CustomProgressDialog(it, R.style.ProgressDialogDim) }
        loadingDialog?.show()
    }

    open fun initView() {

    }
}