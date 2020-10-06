package com.example.oplus.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.adapter.HomeViewPageAdapter
import com.example.oplus.model.ItemResultMenu
import com.example.oplus.viewmodel.MenuViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.ceil

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var menuViewModel: MenuViewModel? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuViewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        menuViewModel?.getMenu()
        menuViewModel?.resultMenu?.observe(viewLifecycleOwner, {
           val listMenu = it?.result?.items
            var countOfPager = (ceil(((listMenu?.size?.toDouble() ?: 0.toDouble())) / 8)).toInt()
            vpMenu.adapter =
                listMenu?.let { it1 -> HomeViewPageAdapter(it1,childFragmentManager,countOfPager) }
            pageIndicatorView.count = countOfPager
            (activity as MainActivity).loadingDialog?.hide()
        })
    }


}