package com.example.oplus.fragment.fish

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.oplus.R
import com.example.oplus.activities.MainActivity
import com.example.oplus.fragment.base.BaseSNCFragment
import com.example.oplus.fragment.base.BaseBacklogFragment
import com.example.oplus.fragment.crops.BacklogCropsFragment
import com.example.oplus.model.inventory.ResultTask
import com.example.oplus.viewmodel.AdoptFishViewModel
import com.example.oplus.viewmodel.BaseTaskViewModel
import kotlinx.android.synthetic.main.fragment_base_task.*

class AdoptFishFragment : BaseSNCFragment() {
    var adoptFishViewModel: AdoptFishViewModel? = null
    var taskAdoptFishFragment: TaskAdoptFishFragment? = null
    var typeS = ""
    override fun initView() {
        adoptFishViewModel = ViewModelProviders.of(this).get(AdoptFishViewModel::class.java)
        adoptFishViewModel?.soLuongCongViec()
        showLoading()
        super.initView()
        fbScan.setImageResource(R.drawable.icon_calendar)
        fbScan.drawable.mutate()
            .setTint(ContextCompat.getColor(activity as MainActivity, R.color.white))
    }

    override fun getViewModel(): BaseTaskViewModel {
        return adoptFishViewModel!!
    }

    override fun getTypeScreen(): String {
        return typeS
    }

    override fun getTitle(): String? {
        return getString(R.string.adopt_fish).toUpperCase()
    }

    override fun getListFragmentInVP(list: MutableList<ResultTask>): MutableList<Fragment>? {
        listFragmentInVp = mutableListOf()
        taskAdoptFishFragment = list.get(0).tabName?.let { TaskAdoptFishFragment(it) }
        taskAdoptFishFragment?.let { listFragmentInVp?.add(it) }
        backlogCropsFragment = list.get(1).tabName?.let { BacklogCropsFragment(it) }
        listFragmentInVp?.add(backlogCropsFragment!!)
        taskAdoptFishFragment = list.get(2).tabName?.let { TaskAdoptFishFragment(it) }
        taskAdoptFishFragment?.let { listFragmentInVp?.add(it) }
        return listFragmentInVp
    }
}