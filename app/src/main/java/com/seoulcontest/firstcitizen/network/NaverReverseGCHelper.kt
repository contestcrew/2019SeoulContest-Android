package com.seoulcontest.firstcitizen.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NaverReverseGCHelper {

    private val retrofit: Retrofit
    val apiService: NaverReverseGCApi

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(NaverReverseGCApi::class.java)
    }

    companion object {
        const val API_URL =
            "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/"

        private var INSTANCE: NaverReverseGCHelper? = null

        fun getInstance(): NaverReverseGCHelper {
            return INSTANCE ?: NaverReverseGCHelper()
        }
    }
}