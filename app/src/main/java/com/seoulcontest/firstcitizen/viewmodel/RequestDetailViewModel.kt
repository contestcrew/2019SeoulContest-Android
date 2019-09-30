package com.seoulcontest.firstcitizen.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.seoulcontest.firstcitizen.data.vo.Report
import com.seoulcontest.firstcitizen.data.vo.Request
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestDetailViewModel {
    val request = ObservableField<Request>()
    val reports = ObservableField<List<Report>>()

    fun loadData(requestId: Int) {
        RetrofitHelper
            .getInstance()
            .apiService
            .getDetailRequestById("Token ${MainViewModel.getInstance().userToken}", requestId)
            .enqueue(object : Callback<Request> {
                override fun onFailure(call: Call<Request>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<Request>, response: Response<Request>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        data?.let { request.set(data) }

                    } else {
                        Log.d("test", "err2 ${response.errorBody()?.string()}")
                        Log.d("test", "err2 ${requestId}")
                    }
                }
            })

        //request requestId 로 해당 request의 report 가져오기
        RetrofitHelper
            .getInstance()
            .apiService
            .getReportsByRequestId("Token ${MainViewModel.getInstance().userToken}", requestId)
            .enqueue(object : Callback<List<Report>> {
                override fun onFailure(call: Call<List<Report>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<Report>>, response: Response<List<Report>>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        reports.set(data)
                    } else {
                        Log.d("test", "err1 ${response.errorBody()?.string()}")
                    }
                }
            })
    }

}