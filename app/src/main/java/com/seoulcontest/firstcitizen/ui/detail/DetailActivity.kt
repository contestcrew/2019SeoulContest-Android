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
