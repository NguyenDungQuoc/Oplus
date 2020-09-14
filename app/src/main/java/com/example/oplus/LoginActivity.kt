package com.example.oplus

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.model.Base
import com.example.oplus.model.LoginModel
import com.example.oplus.viewmodel.LoginViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.custom_dialog.view.*


class LoginActivity : AppCompatActivity() {
    var loginViewModel: LoginViewModel? = null
    var isShow: Boolean = false
    private var sharedPre: SharedPreferences? = null
    private var sharedPreEditor: SharedPreferences.Editor? = null
    private var user: LoginModel? = LoginModel()
    private var stringUser: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


        onClickButton()

        sharedPre = getSharedPreferences("OPLUS_APP", Activity.MODE_PRIVATE)
        sharedPreEditor = sharedPre?.edit()

        stringUser = sharedPre?.getString("USER", "") ?: ""
       if(!stringUser.isNullOrEmpty()){
           user = Gson().fromJson<LoginModel>(
               stringUser,
               object : TypeToken<LoginModel>() {}.type
           )
           etUsername.setText(user?.LoginName)
           etPassword.setText(user?.Password)
       }



        loginViewModel?.result?.observe(this, Observer {
            //Luu thong tin dang nhap
            Base.loginData = it
            user?.LoginName = etUsername.text.toString()
            user?.Password = etPassword.text.toString()


            sharedPreEditor?.putString("USER", Gson().toJson((user)))
            sharedPreEditor?.apply()
            //Tao class luu lai bien ResultLogin de su dung cho toan app
            val intent = Intent(this, MainActivity::class.java)
            this.finish()
            startActivity(intent)
        })
        loginViewModel?.errorMessage?.observe(this, {
            Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
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
            if (userName.isEmpty()) {
                showDialogCustom()
            } else if (password.isEmpty()) {
                showDialogCustom()
            }
            //goi ham dang nhap. Gui thong tin username, pass toi server
            loginViewModel?.login(userName, password)


        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

    private fun showDialogCustom() {
        val builder = AlertDialog.Builder(this)
        val inflater: LayoutInflater = layoutInflater
        val view: View = inflater.inflate(R.layout.custom_dialog, null)
        view.tvError.text = "Vui lòng nhập tên đăng nhập hoặc mật khẩu"
        builder.setView(view)

        val dialog = builder.create()
        view.tvTryAgain.setOnClickListener {
            dialog.dismiss()
        }
        val window: Unit? = dialog.window?.setGravity(Gravity.TOP)

        dialog.show()

    }

}