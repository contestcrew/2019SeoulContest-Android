package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.Request
import com.seoulcontest.firstcitizen.databinding.ActivityRequestHistoryBinding
import com.seoulcontest.firstcitizen.ui.dialog.CategoryDialog

class RequestHistoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityRequestHistoryBinding
    private lateinit var requestArr: Array<Request>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_request_history)

        initView()
        initEvent()

    }

    private fun initView() {

        val requestHistoryManager = LinearLayoutManager(applicationContext)

        with(binding) {

            rvRequestHistoryList.layoutManager = requestHistoryManager
            rvRequestHistoryList.adapter = RequestHistoryAdapter(requestArr)
        }

    }

    private fun initEvent() {

        with(binding) {

            btnRequest.setOnClickListener {
                CategoryDialog(applicationContext).show(supportFragmentManager.beginTransaction(), "")
            }
        }


    }
}
