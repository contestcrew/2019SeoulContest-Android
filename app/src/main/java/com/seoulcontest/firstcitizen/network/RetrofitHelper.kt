package com.seoulcontest.firstcitizen.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    private val retrofit: Retrofit
    val apiService: FirstCitizenApiService

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(FirstCitizenApiService::class.java)
    }

    companion object {
        const val API_URL =
            "http://eb-seoulcontest-deploy-master.ap-northeast-2.elasticbeanstalk.com/"

        private var INSTANCE: RetrofitHelper? = null

        fun getInstance(): RetrofitHelper {
            return INSTANCE ?: RetrofitHelper()
        }
    }
}