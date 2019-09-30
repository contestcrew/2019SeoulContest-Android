package com.seoulcontest.firstcitizen.network

import com.seoulcontest.firstcitizen.network.vo.NaverReverseGCResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverReverseGCApi {
    @GET("gc")
    fun getAddressByLngLat(
        @Header("X-NCP-APIGW-API-KEY-ID") clientId: String,
        @Header("X-NCP-APIGW-API-KEY") secretKey: String,
        @Query("coords") coords: String,
        @Query("output") output: String,
        @Query("orders") orders: String
    ): Call<NaverReverseGCResponse>
}