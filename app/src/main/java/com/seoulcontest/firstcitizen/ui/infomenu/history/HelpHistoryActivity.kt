package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityHistoryHelpBinding
import com.seoulcontest.firstcitizen.network.RetrofitHelper

class HelpHistoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityHistoryHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history_help)
        initView()
        initEvent()
    }

    private fun initView() {

        // todo : 회원의 도움 리스트 정보 가져와서 초기화하기
        val helpHistoryArr = RetrofitHelper().apiService.putRequest()
        binding.rvHelpHistory.adapter = HelpHistoryAdapter(this,)

    }


    private fun initEvent() {


    }
}
