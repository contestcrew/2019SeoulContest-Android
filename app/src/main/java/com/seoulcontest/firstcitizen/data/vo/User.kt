package com.seoulcontest.firstcitizen.data.vo


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: Int,
    @SerializedName("grade")
    val grade: Int,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("manner_score")
    val mannerScore: Int,
    @SerializedName("citizen_score")
    val citizenScore: Int,
    @SerializedName("device_token")
    val deviceToken: String
)