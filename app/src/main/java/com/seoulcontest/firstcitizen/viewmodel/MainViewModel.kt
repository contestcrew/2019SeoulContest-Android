package com.seoulcontest.firstcitizen.viewmodel

import androidx.databinding.ObservableField
import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.data.vo.Category
import com.seoulcontest.firstcitizen.data.vo.User
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel {
    val categoryList = ObservableField<List<Category>>() // 카테고리 리스트
    val briefRequestList = ObservableField<List<BriefRequest>>() // 맵에 보여줄 briefRequest 리스트
    val currRequest = ObservableField<BriefRequest>() // 현재 클릭된 마커의 request 데이터
    val isDataLoaded = ObservableField<Boolean>().apply { set(false) } //맵에 뿌릴 데이터가 불려졌는지 체크하는 변수
    val isLogIn = ObservableField<Boolean>().apply { set(false) } // 현재 로그인상태 체크하는 변수
    val user = ObservableField<User>() // 현재 유저 정보

    fun loadCategoryList() {
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
        isDataLoaded.set(false)

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
                    if (!data.isNullOrEmpty()) {
                        briefRequestList.set(data)
                        isDataLoaded.set(true)
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