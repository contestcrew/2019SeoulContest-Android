package com.seoulcontest.firstcitizen.ui.infomenu.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.gms.common.util.DataUtils
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityHelpDetailBinding

class HelpDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityHelpDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help_detail)

        initView()
        initEvent()
    }

    private fun initView(){


    }

    private fun initEvent(){


    }
}
