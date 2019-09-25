package com.seoulcontest.firstcitizen.data.vo


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("pin_image")
    val pinImage: String
)
