package com.seoulcontest.firstcitizen.data.vo

import com.google.gson.annotations.SerializedName

data class ReportList(
    @SerializedName("request")
    val request: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("is_agreed_inform")
    val isAgreedInform: Boolean,
    @SerializedName("helped_at")
    val helpTime: String,
    @SerializedName("created_at")
    val createdTime: String,
    @SerializedName("updated_at")
    val updateTime: String,
    @SerializedName("images")
    val images : Array<String>
)
