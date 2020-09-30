package com.example.oplus.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oplus.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ChangePasswordBottomDialogFragment: BottomSheetDialogFragment(){

    fun newInstance(): ChangePasswordBottomDialogFragment? {
        return ChangePasswordBottomDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_bottom_sheet, container,
            false
        )
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog;
    }
}