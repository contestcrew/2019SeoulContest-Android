package com.seoulcontest.firstcitizen.data.vo

import com.google.gson.annotations.SerializedName

data class GetTokenResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("user_id")
    val id: String
)