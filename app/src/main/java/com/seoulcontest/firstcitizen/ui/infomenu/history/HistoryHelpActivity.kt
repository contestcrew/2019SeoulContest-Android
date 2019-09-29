package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityHistoryHelpBinding

class HistoryHelpActivity : AppCompatActivity() {

    lateinit var binding: ActivityHistoryHelpBinding
    private var userToken : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history_help)
        initView()
        initEvent()
    }

    private fun initView() {

        if (intent!= null){
            // 로그인한 유저의 토큰값 얻기
            userToken = intent.getStringExtra("userToken")
        }

        //RetrofitHelper.getInstance().apiService.getReportHistory("Token $userToken", GetReportHistory())


    }


    private fun initEvent() {


    }
}
