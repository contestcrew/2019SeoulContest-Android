package com.seoulcontest.firstcitizen.network

import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.data.vo.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FirstCitizenApiService {
    @GET("request/boundary/")
    fun getRequestsByPosition(@Query("latitude") latitude: Float, @Query("longitude") longitude: Float): Call<List<BriefRequest>>

    @GET("request/category/")
    fun getCategoryList(): Call<List<Category>>
}