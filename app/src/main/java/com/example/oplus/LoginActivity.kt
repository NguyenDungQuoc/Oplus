package com.example.oplus


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.model.Base
import com.example.oplus.model.LoginModel
import com.example.oplus.model.SharedPreferencesManager
import com.example.oplus.viewmodel.LoginViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.custom_dialog_loading.*


class LoginActivity : AppCompatActivity() {
    private var loginViewModel: LoginViewModel? = null
    private var isShow: Boolean = false
    private var user: LoginModel? = LoginModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        if (!isNetworkConnected()) {
            showDialogCustom(2, "Kiểm tra lại kết nối mạng của bạn")
        }

        onClickButton()

        val userLogin =
            SharedPreferencesManager.getString(this, SharedPreferencesManager.KEY_USER, "")

        etUsername.setText(user?.LoginName)
        etPassword.setText(user?.Password)

        loginViewModel?.result?.observe(this, Observer {
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

        })

        loginViewModel?.errorMessage?.observe(this, {
            showDialogCustom(1, "Sai tên đăng nhập hoặc mật khẩu")
        })
        loginViewModel?.tilte?.observe(this, {

        })
    }

    private fun onClickButton() {
        btnShowPass.setOnClickListener {

            isShow = !(isShow)
            if (isShow) {
                btnShowPass.setImageResource(R.drawable.icon_see_pass)
                etPassword.transformationMethod = null
            } else {
                btnShowPass.setImageResource(R.drawable.icon_no_see_pass)
                etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        btnLogin.setOnClickListener {
            val userName = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (userName.isEmpty() || password.isEmpty()) {
                showDialogCustom(1, "Vui lòng nhập tên đăng nhập hoặc mật khẩu")
            }
            //goi ham dang nhap. Gui thong tin username, pass toi server
            loginViewModel?.login(userName, password)
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected

    }
    private fun showDialogCustom(type: Int, message: String) {
        val builder = AlertDialog.Builder(this)
        val inflater: LayoutInflater = layoutInflater
        val view: View = inflater.inflate(R.layout.custom_dialog, null)
        view.tvError.text = message
        builder.setView(view)
        val dialog = builder.create()
        view.tvTryAgain.setOnClickListener {
            dialog.dismiss()
        }
        val window: Unit? = dialog.window?.setGravity(Gravity.TOP)
        when (type) {
            1 -> {
                dialog.show()
            }
            2 -> {
                view.tvTryAgain.visibility = View.GONE
                view.viewLine.visibility = View.GONE
                view.imgError.setImageResource(R.drawable.icon_no_wifi)
                dialog.show()
            }
        }


    }

}