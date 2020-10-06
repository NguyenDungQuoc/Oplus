package com.example.oplus.activities



import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.CustomProgressDialog
import com.example.oplus.R
import com.example.oplus.model.SharedPreferencesManager
import com.example.oplus.model.base.Base
import com.example.oplus.model.login.LoginModel
import com.example.oplus.viewmodel.LoginViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {
    private var loginViewModel: LoginViewModel? = null
    private var isShow: Boolean = false
    private var user: LoginModel? = LoginModel()

    override fun initView() {
        super.initView()
        loadingDialog = CustomProgressDialog(this, R.style.ProgressDialogDim)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        checkConnected()
        onClickButton()
        val userLogin =
            SharedPreferencesManager.getString(this, SharedPreferencesManager.KEY_USER, "")
        etUsername.setText(user?.LoginName)
        etPassword.setText(user?.Password)
        viewModelObserve()
    }
    override fun getResource(): Int {
        return R.layout.activity_login
    }

    override fun getBackImage(): View? {
        return null
    }

    private fun viewModelObserve() {
        loginViewModel?.result?.observe(this, {
            //Luu thong tin dang nhap
            Base.loginData = it
            user?.LoginName = etUsername.text.toString()
            user?.Password = etPassword.text.toString()
            user?.isLogin = true

            SharedPreferencesManager.writeString(
                this,
                SharedPreferencesManager.KEY_USER,
                Gson().toJson((user))
            )
            loadingDialog?.hide()
            val intent = Intent(this, MainActivity::class.java)
            this.finish()
            startActivity(intent)
        })

        loginViewModel?.errorMessage?.observe(this, {
            showDialogCustom(1, getString(R.string.check_username_password))
            loadingDialog?.hide()
        })
    }

    private fun onClickButton() {
        btnShowPass.setOnClickListener {
            changeIconShowPass()

        }
        btnLogin.setOnClickListener {
            loadingDialog?.show()
            val userName = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (userName.isEmpty() || password.isEmpty()) {
                showDialogCustom(1, getString(R.string.check_empty_user))
            }
            //goi ham dang nhap. Gui thong tin username, pass toi server
            loginViewModel?.login(userName, password)

        }

        etUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) {
                    phoneActive(true)
                }else{
                    phoneActive(false)
                }
            }
        })

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.length != 0) {
                    passActive(true)
                }else{
                    passActive(false)
                }
            }
        })


    }

    private fun changeIconShowPass() {
        isShow = !(isShow)
        if (isShow) {
            btnShowPass.setImageResource(R.drawable.icon_see_pass)
            etPassword.transformationMethod = null
        } else {
            btnShowPass.setImageResource(R.drawable.icon_no_see_pass)
            etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    fun passActive(status:Boolean){
        imgLock.isSelected = status
        viewPassword.isSelected = status
    }
    fun phoneActive(status:Boolean){
        imgPhone.isSelected = status
        viewUsername.isSelected = status
    }



}