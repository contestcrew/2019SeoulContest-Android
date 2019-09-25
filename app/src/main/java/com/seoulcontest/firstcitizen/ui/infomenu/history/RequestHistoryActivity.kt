package com.seoulcontest.firstcitizen.ui.infomenu.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityRequestHistoryBinding

class RequestHistoryActivity : AppCompatActivity() {

    lateinit var binding : ActivityRequestHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_request_history)
    }
}
