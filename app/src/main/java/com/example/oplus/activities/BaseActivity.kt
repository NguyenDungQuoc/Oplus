package com.example.oplus.activities


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.oplus.CustomProgressDialog
import com.example.oplus.R
import kotlinx.android.synthetic.main.custom_dialog.view.*

open abstract class BaseActivity : AppCompatActivity() {
    var loadingDialog: CustomProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getResource())
        initView()
    }

    open fun initView() {

    }

    abstract fun getResource(): Int

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected

    }

    fun checkConnected() {
        if (!isNetworkConnected()) {
            showDialogCustom(2, getString(R.string.check_connect))
        }
    }

    fun showDialogCustom(type: Int, message: String) {
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

    fun showFragment(frag: Fragment, isAdd: Boolean = false) {
        frag.let {
            try {
                val transaction = supportFragmentManager.beginTransaction()

                //hide other
                supportFragmentManager.fragments.forEach {
                    if (it != frag) {
                        transaction.hide(it)
                    }
                }

                if (!frag.isAdded) {
                    transaction.add(R.id.contentDashboard, frag)
                    if (isAdd) {
                        transaction.addToBackStack(null)
                    }
                } else {
                    transaction.show(frag)
                }
                transaction.commit()

            } catch (e: Exception) {
            }
        }
    }
}