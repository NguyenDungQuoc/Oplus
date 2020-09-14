package com.example.oplus

import android.os.Bundle
import android.view.View

import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.oplus.model.Base
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    var user = Base.loginData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            Glide.with(imgBg.context).load(R.drawable.bg_profile).into(imgBg)
            Glide.with(imgAvatarProfile.context).load(user?.Hinh).error(R.drawable.icon_avatar).into(imgAvatarProfile)
            tvNameProfile.text = user?.Name
        }

    }


}