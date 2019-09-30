package com.seoulcontest.firstcitizen.data.vo

import com.google.gson.annotations.SerializedName

data class Report(
    @SerializedName("request")
    val request: Int,
    @SerializedName("author")
    val author: Author,
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
    val images: List<String>
) {
    data class Author(
        @SerializedName("id")
        val id: Int,
        @SerializedName("nickname")
        val nickname: String
    )
}
