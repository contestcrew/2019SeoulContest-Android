package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.GetReportHistory
import com.seoulcontest.firstcitizen.data.vo.Report
import com.seoulcontest.firstcitizen.databinding.ActivityHistoryHelpBinding
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryHelpActivity : AppCompatActivity() {

    lateinit var binding: ActivityHistoryHelpBinding
    lateinit var helpHistoryList: ArrayList<Report>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history_help)
        loadData()
        initView()
        initEvent()
    }

    private fun initView() {
        //binding.rvHelpHistory.adapter = HistoryHelpAdapter()
    }

    private fun loadData() {
        Log.d("history", "Token : ${MainViewModel.getInstance().userToken}")
        RetrofitHelper.getInstance().apiService.getReportHistory("Token ${MainViewModel.getInstance().userToken}")
            .enqueue(object : Callback<GetReportHistory> {
                override fun onFailure(call: Call<GetReportHistory>, t: Throwable) {
                    t.printStackTrace()
                    Log.d("history", "failed")
                }

                override fun onResponse(call: Call<GetReportHistory>, response: Response<GetReportHistory>) {

                    if (response.isSuccessful) {
                        Log.d("history", response.body().toString())

                    } else {
                        Log.d("history", response.errorBody().toString())
                    }
                }

            })
    }

    private fun initEvent() {

    }
}
