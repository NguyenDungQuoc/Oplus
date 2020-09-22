package com.example.oplus.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.oplus.R
import kotlinx.android.synthetic.main.fragment_img_device.*

class ImgDeviceFragment : Fragment(R.layout.fragment_img_device) {
    private var imgUrl: String? = null
    companion object{
        fun newInstance(img: String?) = ImgDeviceFragment().apply {
            this.imgUrl = img
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(imgDevice.context).load(imgUrl).error(R.drawable.empty).into(imgDevice)
    }
}