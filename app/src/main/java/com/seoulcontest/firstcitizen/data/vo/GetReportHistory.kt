package com.seoulcontest.firstcitizen.data.vo

import com.google.gson.annotations.SerializedName

data class GetReportHistory(
    @SerializedName("request")
    val request: Int,
    @SerializedName("author")
    val author: Author,
    @SerializedName("title")
    val title: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("is_agreed_inform")
    val isAgreed: Boolean,
    @SerializedName("helped_at")
    val helpTime: String
) {
    data class Author(
        @SerializedName("id")
        val id: Int,
        @SerializedName("nickname")
        val nickname: String
    )
}