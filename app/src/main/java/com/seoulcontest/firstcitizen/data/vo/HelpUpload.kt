package com.seoulcontest.firstcitizen.data

import com.google.gson.annotations.SerializedName

data class HelpUpload(
    @SerializedName("request")
    val request : Int,
    @SerializedName("author")
    val author : Int,
    @SerializedName("title")
    val title : Int,
    @SerializedName("content")
    val content : Int,
    @SerializedName("is_agreed_inform")
    val isAgreedInform : Int,
    @SerializedName("helped_at")
    val helpedAt : Int,
    @SerializedName("created_at")
    val createdAt : Int,
    @SerializedName("updated_At")
    val updatedAt : Int,
    @SerializedName("images")
    val images : String
)