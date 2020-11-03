package com.example.oplus.fragment.fish

import com.example.oplus.ScreenIDEnum
import com.example.oplus.fragment.base.BaseSearchFragment
import com.example.oplus.model.base.Base
import com.example.oplus.model.search.BaseSearchRequestDTO
import kotlinx.android.synthetic.main.fragment_base_search.*

class SearchFishFragment : BaseSearchFragment() {
    override fun initView() {
        super.initView()
        Base.loginData?.WebUrl = "nc"
        onClickListener()
    }

    private fun onClickListener() {
        tvSearch.setOnClickListener {
            rq = BaseSearchRequestDTO().apply {
                key = edtBaseSearch.text.toString()
                tuNgay = checkFromDate()
                denNgay = checkToDate()
                trangThai = defaultStt
                pagingInfo = null
            }
            searchViewModel?.timcongviec(rq ?: BaseSearchRequestDTO())
        }
    }

    override fun getTypeScreen(): String {
        return ScreenIDEnum.ADOPT_FISH_SCREEN.value
    }
}