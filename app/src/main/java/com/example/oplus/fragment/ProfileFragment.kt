package com.example.oplus.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.model.Base
import com.example.oplus.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var user = Base.loginData
    private var loginViewModel: LoginViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel?.getCurrentUserProfile()
        setView()
        onClickText()
    }

    private fun setView() {

        loginViewModel?.currentUserProfile?.observe(viewLifecycleOwner, {
            Base.currentUserProfile = it
            view.apply {
                Glide.with(imgBg.context).load(R.drawable.bg_profile).into(imgBg)
                Glide.with(imgAvatarProfile.context).load(user?.Hinh).error(R.drawable.icon_avatar)
                    .into(
                        imgAvatarProfile
                    )
                tvNameProfile.text = user?.Name?.toUpperCase()
                tvUserNameProfile.text = user?.UserName
                tvEmail.text = user?.Email
                tvPosition.text = Base.currentUserProfile?.chucVu
                tvFarm.text = Base.currentUserProfile?.siteName ?: ""
            }
        })
    }

    private fun onClickText() {
        clFarm.setOnClickListener {

        }
        clChangePass.setOnClickListener {

        }
        clSetting.setOnClickListener {

        }
        clLanguage.setOnClickListener {

        }
        clLogOut.setOnClickListener {
//           val intent = Intent(it, LoginActivity::class.java)
//           intent.putExtra("finish", true) // if you are checking for this in your other Activities
//
//           intent. = Intent.FLAG_ACTIVITY_CLEAR_TOP or
//                   Intent.FLAG_ACTIVITY_CLEAR_TASK or
//                   Intent.FLAG_ACTIVITY_NEW_TASK
//           startActivity(intent)
//           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//           startActivity(intent)
//       }
        }


    }
}