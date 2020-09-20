package com.example.oplus

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.model.Base
import com.example.oplus.model.LoginModel
import com.example.oplus.model.SharedPreferencesManager
import com.example.oplus.viewmodel.LoginViewModel
import com.google.gson.Gson


class SplashScreenActivity : AppCompatActivity() {
    private var loginViewModel: LoginViewModel? = null
    private var user: LoginModel? = LoginModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val handler = Handler()
        val userLogin =
            SharedPreferencesManager.getString(this, SharedPreferencesManager.KEY_USER, "")
        val g = Gson()
        user = g.fromJson(userLogin, LoginModel::class.java)

        val userName = user?.LoginName
        val password = user?.Password
        if (user?.isLogin == true) {
            if (userName != null && password != null) {
                loginViewModel?.result?.observe(this, {
                    Base.loginData = it
                    val intent = Intent(this, MainActivity::class.java)
                    this.finish()
                    startActivity(intent)
                })
                loginViewModel?.login(userName, password)
            }
            loginViewModel?.errorMessage?.observe(this, {
                handler.postDelayed({
                    val intent = Intent(this, LoginActivity::class.java)
                    this.finish()
                    startActivity(intent)
                }, 1000)
            })

        } else {
            handler.postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                this.finish()
                startActivity(intent)
            }, 1000)
        }

    }
}