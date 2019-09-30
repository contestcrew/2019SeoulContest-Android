package com.seoulcontest.firstcitizen.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.seoulcontest.firstcitizen.data.vo.Report
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportDetailViewModel {
    val report = ObservableField<Report>()

    fun loadData(reportId: Int) {
        RetrofitHelper
            .getInstance()
            .apiService
            .getReportById("Token ${MainViewModel.getInstance().userToken}", reportId)
            .enqueue(object : Callback<Report> {
                override fun onFailure(call: Call<Report>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<Report>, response: Response<Report>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.let { report.set(data) }

                    } else {
                        Log.d("test", "err2 ${response.errorBody()?.string()}")
                        Log.d("test", "err2 ${reportId}")
                    }
                }
            })
    }

}