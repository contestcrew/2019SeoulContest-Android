package com.seoulcontest.firstcitizen.ui.upload

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityDetailAddressBinding

class DetailAddressActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_address)

        initEvent()
    }

    private fun initEvent() {

    }
}

