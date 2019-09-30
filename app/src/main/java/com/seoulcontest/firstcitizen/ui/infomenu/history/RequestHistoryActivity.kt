package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.databinding.ActivityRequestHistoryBinding
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import com.seoulcontest.firstcitizen.ui.dialog.CategoryDialog
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestHistoryBinding
    private lateinit var adapter: RequestHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_request_history)

        initView()
        initEvent()
        initData()
    }

    private fun initData() {
        RetrofitHelper
            .getInstance()
            .apiService
            .getRequestsByToken("Token ${MainViewModel.getInstance().userToken}")
            .enqueue(object : Callback<List<BriefRequest>> {
                override fun onFailure(call: Call<List<BriefRequest>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<BriefRequest>>, response: Response<List<BriefRequest>>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        adapter.setData(data)

                    } else {
                        Log.d("test", "ERROR : ${response.errorBody()?.string()}")
                    }
                }
            })
    }

    private fun initView() {
        adapter = RequestHistoryAdapter()
        binding.rvRequestHistoryList.adapter = adapter
    }

    private fun initEvent() {
        binding.btRequest.setOnClickListener {
            CategoryDialog(applicationContext).show(supportFragmentManager.beginTransaction(), "")
        }
    }
}
