package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.Report
import com.seoulcontest.firstcitizen.data.vo.Request
import com.seoulcontest.firstcitizen.databinding.ActivityHistoryHelpBinding
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryHelpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryHelpBinding
    private var helpHistoryList: List<Report> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history_help)
        loadData()
        initView()
        initEvent()
    }

    private fun initView() {
        binding.rvHelpHistory.adapter = HistoryHelpAdapter(helpHistoryList)
    }

    private fun loadData() {

        RetrofitHelper.getInstance()
            .apiService
            .getReportListByToken("Token ${MainViewModel.getInstance().userToken}")
            .enqueue(object : Callback<List<Report>> {
                override fun onFailure(call: Call<List<Report>>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("history", "failed")
                }

                override fun onResponse(call: Call<List<Report>>, response: Response<List<Report>>) {

                    if (response.isSuccessful) {
                        val result = response.body()

                        if (result != null) {
                            helpHistoryList = result
                        }

                    } else {
                        Log.d("history", response.errorBody().toString())
                    }
                }

            })
    }

    private fun initEvent() {

    }
}
