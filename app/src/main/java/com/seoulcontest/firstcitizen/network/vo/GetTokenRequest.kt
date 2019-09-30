package com.seoulcontest.firstcitizen.network.vo

import com.google.gson.annotations.SerializedName

data class GetTokenRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)