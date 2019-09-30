package com.seoulcontest.firstcitizen.network

import com.seoulcontest.firstcitizen.network.vo.NaverPlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverPlaceApi {
    @GET("search")
    fun getAddressByQuery(
        @Header("X-NCP-APIGW-API-KEY-ID") clientId: String,
        @Header("X-NCP-APIGW-API-KEY") secretKey: String,
        @Query("query") query: String,
        @Query("coordinate") coordinate: String,
        @Query("orderby") orderby: String
    ): Call<NaverPlaceResponse>
}