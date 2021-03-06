package com.example.oplus.activities


import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.fragment.HomeFragment
import com.example.oplus.fragment.profile.ProfileFragment
import com.example.oplus.R
import com.example.oplus.ScreenIDEnum
import com.example.oplus.activities.base.BaseActivity
import com.example.oplus.fragment.QRScanNavigationFragment
import com.example.oplus.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    var qrScanNavigationFragment: QRScanNavigationFragment? = null
    private var menuViewModel: MenuViewModel? = null
    private var profileFragment: ProfileFragment = ProfileFragment()
    private var homeFragment: HomeFragment = HomeFragment()
    var type = ScreenIDEnum.QR_SCAN_SCREEN_FROM_NAVIGATION.value
    override fun initView() {
        super.initView()

        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        showFragment(homeFragment)

        onClickMenuBottom()
    }
    override fun getResource(): Int {
        return R.layout.activity_main
    }

    override fun getBackImage(): View? {
        return null
    }


    private fun onClickMenuBottom() {
        val badge = menuBottomDashboard.getOrCreateBadge(R.id.navigation_notification)
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
                    qrScanNavigationFragment = QRScanNavigationFragment()
                    qrScanNavigationFragment?.type = type
                    showFragment(qrScanNavigationFragment!!,true)
                    true
                }
                R.id.navigation_notification -> {
                    true
                }
                R.id.navigation_profile -> {
                   showFragment(profileFragment!!)
                    true
                }
                else -> false
            }
        }
    }
}