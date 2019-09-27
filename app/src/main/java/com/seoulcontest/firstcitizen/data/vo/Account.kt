package com.seoulcontest.firstcitizen.data.vo

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("device_token")
    val deviceToken: String)