package com.seoulcontest.firstcitizen.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.data.vo.Category
import com.seoulcontest.firstcitizen.data.vo.User
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel {
    val isLogIn = ObservableField<Boolean>().apply { set(false) } // 현재 로그인상태 체크하는 변수
    val user = ObservableField<User>() // 현재 유저 정보
    var userToken = ""
    var currLatitude = 0.0
    var currLongitude = 0.0

    val categoryList = ObservableField<List<Category>>() // 카테고리 리스트
    val briefRequestList = ObservableField<List<BriefRequest>>() // 맵에 보여줄 briefRequest 리스트
    val currRequest = ObservableField<BriefRequest>() // 현재 클릭된 마커의 request 데이터
    val isDataLoaded = ObservableField<Boolean>().apply { set(false) } //맵에 뿌릴 데이터가 불려졌는지 체크하는 변수
    val isMarkerClicked = ObservableField<Boolean>().apply { set(false) } //마커가 눌려졌는지 체크하는 변수
    val tvBottomStatus = ObservableField<String>().apply { "주변에 곤경에 빠진 시민이 없습니다" } // 맵하단에 보여줄 상태 문자열

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
                        val tempCategoryList =
                            mutableListOf(Category(0, "전체", 0, "", ""))

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

        if (isLogIn.get()!!) {
            val userToken = "Token $userToken"

            Log.d("test", "token : $userToken")

            //서버에서 데이터 가져오기
            RetrofitHelper
                .getInstance()
                .apiService
                .getRequestsByPositionWithToken(userToken, x, y)
                .enqueue(object : Callback<List<BriefRequest>> {
                    override fun onFailure(call: Call<List<BriefRequest>>, t: Throwable) {
                        t.printStackTrace()
                        isDataLoaded.set(true)
                    }

                    override fun onResponse(
                        call: Call<List<BriefRequest>>,
                        response: Response<List<BriefRequest>>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            if (!data.isNullOrEmpty()) {
                                briefRequestList.set(data)
                                tvBottomStatus.set("곤경에 빠진 시민을 도와주세요")
                            } else {
                                tvBottomStatus.set("주변에 곤경에 빠진 시민이 없습니다")
                            }
                        } else {
                            Log.d("test", "err ${response.errorBody()?.string()}")
                        }
                        isDataLoaded.set(true)
                    }
                })
        } else {

            Log.d("test", "no token")
            RetrofitHelper
                .getInstance()
                .apiService
                .getRequestsByPositionWithNoToken(x, y)
                .enqueue(object : Callback<List<BriefRequest>> {
                    override fun onFailure(call: Call<List<BriefRequest>>, t: Throwable) {
                        t.printStackTrace()
                        isDataLoaded.set(true)
                    }

                    override fun onResponse(
                        call: Call<List<BriefRequest>>,
                        response: Response<List<BriefRequest>>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            if (!data.isNullOrEmpty()) {
                                briefRequestList.set(data)
                                tvBottomStatus.set("곤경에 빠진 시민을 도와주세요")
                            } else {
                                tvBottomStatus.set("주변에 곤경에 빠진 시민이 없습니다")
                            }
                        } else {
                            Log.d("test", "err ${response.errorBody()?.string()}")
                        }
                        isDataLoaded.set(true)
                    }
                })
        }
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

    fun loadBriefRequestByQuery(query: String?): List<BriefRequest>? {
        return if (query.isNullOrEmpty()) {
            briefRequestList.get()
        } else {
            val data = briefRequestList.get()
            data?.let { list ->
                list.filter {
                    it.title.contains(query) || it.content.contains(query)
                }
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