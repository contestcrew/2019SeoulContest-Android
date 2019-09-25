package com.seoulcontest.firstcitizen.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.data.vo.Category
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel {

    val categoryList = ObservableField<List<Category>>()
    val briefRequestList = ObservableField<List<BriefRequest>>()
    // val logInStatus = ObservableBoolean<>
    val currRequest = ObservableField<BriefRequest>()

    fun loadData(x: Float, y: Float) {
        loadCategoryList()
        loadBriefRequestList(x, y)
    }

    private fun loadCategoryList() {
        //서버에서 데이터 가져오기
        RetrofitHelper
            .getInstance()
            .apiService
            .getCategoryList()
            .enqueue(object : Callback<List<Category>> {
                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<Category>>,
                    response: Response<List<Category>>
                ) {
                    val data = response.body()

                    if (data != null) {
                        val tempCategoryList = mutableListOf(Category(0, "전체", 0, "", ""))

                        for (item in data) {
                            tempCategoryList.add(item)
                        }

                        categoryList.set(tempCategoryList)
                    }
                }
            })
    }

    fun loadBriefRequestList(x: Float, y: Float) {
        //서버에서 데이터 가져오기
        RetrofitHelper
            .getInstance()
            .apiService
            .getRequestsByPosition(x, y)
            .enqueue(object : Callback<List<BriefRequest>> {
                override fun onFailure(call: Call<List<BriefRequest>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<BriefRequest>>,
                    response: Response<List<BriefRequest>>
                ) {
                    val data = response.body()
                    if (data != null) {
                        briefRequestList.set(data)
                    }
                }
            })
    }

    fun loadBriefRequestsByCategory(categoryId: Int): List<BriefRequest>? {
        return if (categoryId == 0) {
            briefRequestList.get()
        } else {
            briefRequestList.get()?.filter {
                it.category == categoryId
            }
        }


    }

    companion object {
        private var INSTANCE: MainViewModel? = null

        fun getInstance() = INSTANCE ?: MainViewModel().apply {
            INSTANCE = this
        }
    }

}