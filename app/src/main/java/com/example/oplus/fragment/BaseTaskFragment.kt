package com.example.oplus.fragment


import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.fragment.failure.BaseSearchFragment
import kotlinx.android.synthetic.main.fragment_base_task.*
import kotlinx.android.synthetic.main.toolbar_menu_dashboard.*

abstract class BaseTaskFragment : BaseFragment(R.layout.fragment_base_task) {
    var searchFragment : BaseSearchFragment? = null
    override fun initView() {
        tvTitleMenu.text = getTitle()
        imgBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
        fbScan.drawable.mutate()
            .setTint(ContextCompat.getColor(activity as MainActivity, R.color.white))
        imgSearch.setOnClickListener {
            searchFragment = BaseSearchFragment()
            (activity as MainActivity).showFragment(searchFragment!!,true)
        }
    }

    abstract fun getTitle(): String?
//    fun showFragment(frag: Fragment, isAdd: Boolean = false) {
//        frag.let {
//            try {
//                val transaction = childFragmentManager.beginTransaction()
//                //hide other
//                childFragmentManager.fragments.forEach {
//                    if (it != frag) {
//                        transaction.hide(it)
//                    }
//                }
//
//                if (!frag.isAdded) {
//                    transaction.add(R.id.llReplace, frag)
//                    if (isAdd) {
//                        transaction.addToBackStack(null)
//                    }
//                } else {
//                    transaction.show(frag)
//                }
//                transaction.commit()
//
//            } catch (e: Exception) {
//            }
//        }
//    }
}