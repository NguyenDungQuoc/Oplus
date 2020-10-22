package com.example.oplus.fragment.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.extensions.toObject
import com.example.oplus.model.SharedPreferencesManager
import com.example.oplus.model.login.LoginModel
import com.example.oplus.viewmodel.LoginViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*


class ChangePasswordBottomDialogFragment : BottomSheetDialogFragment() {
    private var isShow: Boolean = false
    private var loginViewModel: LoginViewModel? = null
    private var loginModel: LoginModel? = null
    fun newInstance(): ChangePasswordBottomDialogFragment? {
        return ChangePasswordBottomDialogFragment()
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog;
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val userLogin =
            activity?.let {
                SharedPreferencesManager.getString(
                    it,
                    SharedPreferencesManager.KEY_USER,
                    ""
                )
            }

        loginModel = userLogin.toObject<LoginModel>()

        val view = inflater.inflate(
            R.layout.fragment_bottom_sheet, container,
            false
        )
        loginViewModel?.newResult?.observe(viewLifecycleOwner,{
            dismiss()
        })
        view.apply {
            tvXacNhan.isEnabled = false
            tvXacNhan.isSelected = true
            etNewPassword.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    val newPassword = etNewPassword.text.toString()
                    val newPasswordAgain = etNewPasswordAgain.text.toString()
                    if (newPassword == newPasswordAgain && newPassword.length != 0) {
                        tvXacNhan.isEnabled = true
                        tvXacNhan.isSelected = false
                    }else{
                        tvXacNhan.isEnabled = false
                        tvXacNhan.isSelected = true
                    }

                }
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                }
            })
            etNewPasswordAgain.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    val newPassword = etNewPassword.text.toString()
                    val newPasswordAgain = etNewPasswordAgain.text.toString()
                    if (newPassword == newPasswordAgain && newPassword.length != 0) {
                        tvXacNhan.isEnabled = true
                        tvXacNhan.isSelected = false
                    }else{
                        tvXacNhan.isEnabled = false
                        tvXacNhan.isSelected = true
                    }
                }
                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                }
            })
            onClickEvent(view)
        }
        return view
    }

    private fun onClickEvent(view: View) {
        view.apply {
            btnShowPass1.setOnClickListener {
                changeIconShowPass(btnShowPass1, etCurrentPassword)
            }
            btnShowPass2.setOnClickListener {
                changeIconShowPass(btnShowPass2, etNewPassword)
            }

            btnShowPass3.setOnClickListener {
                changeIconShowPass(btnShowPass3, etNewPasswordAgain)
            }
            tvXacNhan.setOnClickListener {
                val password = etCurrentPassword.text.toString()
                if (password != loginModel?.Password) {
                    (activity as MainActivity).showDialogCustom(
                        1,
                        "Đổi mật khẩu không thành công.Vui lòng kiểm tra mật khẩu hiện tại !"
                    )
                }else{
                    loginViewModel?.doiMatKhau(password,etNewPasswordAgain.text.toString())
                }
            }
            tvHuy.setOnClickListener {
                this@ChangePasswordBottomDialogFragment.dismiss()
            }
        }


    }

    private fun changeIconShowPass(imageView: ImageView, editText: EditText) {
        isShow = !(isShow)
        if (isShow) {
            imageView.setImageResource(R.drawable.icon_see_pass)
            editText.transformationMethod = null
        } else {
            imageView.setImageResource(R.drawable.icon_no_see_pass)
            editText.transformationMethod = PasswordTransformationMethod.getInstance()
        }
        editText.setSelection(editText.length())
    }

}