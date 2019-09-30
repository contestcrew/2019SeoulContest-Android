package com.seoulcontest.firstcitizen.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NaverPlaceHelper {

    private val retrofit: Retrofit
    val apiService: NaverPlaceApi

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(NaverPlaceApi::class.java)
    }

    companion object {
        const val API_URL =
            "https://naveropenapi.apigw.ntruss.com/map-place/v1/"

        private var INSTANCE: NaverPlaceHelper? = null

        fun getInstance(): NaverPlaceHelper {
            return INSTANCE ?: NaverPlaceHelper()
        }
    }
}