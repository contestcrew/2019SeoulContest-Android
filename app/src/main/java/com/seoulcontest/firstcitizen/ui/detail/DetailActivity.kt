package com.seoulcontest.firstcitizen.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityDetailBinding
import com.seoulcontest.firstcitizen.ui.dialog.HelpDialog

class DetailActivity : AppCompatActivity() {

    // todo : enum class 만드는 거 어떻게 생각하시는지???
    // todo : Request 객체 아래 properties로 구분하자는 의견(서버)

    // 뷰 바인딩
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        initView()
        initEvent()
    }

    // 2019.09.20 뷰 초기화 by Hudson
    private fun initView() {
        initRecyclerView()
    }

    private fun initRecyclerView() {

    }

    private fun initEvent() {
        binding.btHelp.setOnClickListener {
            HelpDialog().show(supportFragmentManager.beginTransaction(), "")
        }
    }

}
