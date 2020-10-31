package com.example.oplus.activities


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.oplus.CustomProgressDialog
import com.example.oplus.R
import kotlinx.android.synthetic.main.custom_dialog.view.*
import java.util.*

abstract class BaseActivity : AppCompatActivity() {
    var loadingDialog: CustomProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getResource())
        loadingDialog = CustomProgressDialog(this, R.style.ProgressDialogDim)

        initView()

    }

    open fun initView() {
        getBackImage()?.setOnClickListener {
            onBackPressed()
        }
    }

    fun showLoading(){
        loadingDialog?.show()
    }
    fun hideLoading(){
        loadingDialog?.hide()
    }
    abstract fun getResource(): Int
    abstract fun getBackImage(): View?

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

     open fun clearBackStack() {
        val manager: FragmentManager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first: FragmentManager.BackStackEntry = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
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
                if (!isAdd) {
                    clearBackStack()
                }


                if (!frag.isAdded) {
                    transaction.add(R.id.contentDashboard, frag)
                    if (isAdd) {
                        transaction.addToBackStack(null)
                    }
                } else {
//                    val index: Int = supportFragmentManager.fragments.indexOf(frag)
//                    Collections.swap(supportFragmentManager.fragments,index,supportFragmentManager.fragments.size -1)
                    transaction.show(frag)
                }
                transaction.commit()

            } catch (e: Exception) {
            }
        }
    }
    fun initToolbar(textView: TextView, title: String){
        textView.text = title.toUpperCase(Locale.ROOT)
    }

}