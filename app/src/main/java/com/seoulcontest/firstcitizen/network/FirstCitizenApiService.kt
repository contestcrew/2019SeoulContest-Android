package com.seoulcontest.firstcitizen.network

import com.seoulcontest.firstcitizen.data.vo.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface FirstCitizenApiService {
    @GET("request/boundary/")
    fun getRequestsByPosition(@Query("latitude") latitude: Float, @Query("longitude") longitude: Float): Call<List<BriefRequest>>

    @GET("request/category/")
    fun getCategoryList(): Call<List<Category>>

    @PUT("request/")
    fun putRequest()

    @POST("account/")
    fun createAccount(@Body pararm: Account): Call<User>

    @POST("account/get_token")
    fun getAccountToken()

    @Multipart
    @POST("request/")
    fun createRequest(
        @Header("Authorization") token: String, @Part("category") type: RequestBody, @Part("content") c1: RequestBody, @Part(
            "title"
        ) t1: RequestBody, @Part("latitude") lati: RequestBody, @Part("longitude") longi: RequestBody, @Part pic: List<MultipartBody.Part>
    ): Call<Request>
}