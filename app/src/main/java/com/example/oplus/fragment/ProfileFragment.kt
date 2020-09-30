package com.example.oplus.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.oplus.R
import com.example.oplus.activities.LoginActivity
import com.example.oplus.model.base.Base
import com.example.oplus.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.*


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var user = Base.loginData
    private var loginViewModel: LoginViewModel? = null
    private var bottomDialogFragment: ChangePasswordBottomDialogFragment? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel?.getCurrentUserProfile()
        setView()
        onClickText()
        bottomDialogFragment = ChangePasswordBottomDialogFragment().newInstance()

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
                tvNameProfile.text = user?.Name?.toUpperCase(Locale.ROOT)
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
            fragmentManager?.let {
                bottomDialogFragment?.show(
                    it,
                    "add_photo_dialog_fragment"
                )
            }
        }
        clSetting.setOnClickListener {

        }
        clLanguage.setOnClickListener {

        }
        clLogOut.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}