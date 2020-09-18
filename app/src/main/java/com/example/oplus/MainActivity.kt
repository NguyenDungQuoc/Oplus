package com.example.oplus

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oplus.adapter.MenuAdapter
import com.example.oplus.model.Base
import com.example.oplus.viewmodel.LoginViewModel
import com.example.oplus.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var loginViewModel: LoginViewModel? = null
    private var menuViewModel: MenuViewModel? = null
    private var menuAdapter: MenuAdapter? = null
    private var profileFragment:ProfileFragment = ProfileFragment()
    private var homeFragment:HomeFragment = HomeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        showFragment(homeFragment)


        onClickMenuBottom()


    }


    private fun onClickMenuBottom() {
        var badge = menuBottomDashboard.getOrCreateBadge(R.id.navigation_notification)
        badge.isVisible = true
        badge.number = 99
        menuBottomDashboard.itemIconTintList = null
        menuBottomDashboard.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_home -> {
                    showFragment(homeFragment)
                    true
                }
                R.id.navigation_calendar -> {

                    true
                }
                R.id.navigation_QR -> {

                    true
                }
                R.id.navigation_notification -> {


                    true
                }
                R.id.navigation_profile -> {
                   showFragment(profileFragment)
                    true
                }
                else -> false


            }
        }
    }
     fun showFragment(frag: Fragment, isAdd:Boolean = false) {

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
                    if(isAdd) {
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