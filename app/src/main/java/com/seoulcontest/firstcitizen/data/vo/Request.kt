package com.seoulcontest.firstcitizen.data.vo


import com.google.gson.annotations.SerializedName

data class Request(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: Int,
    @SerializedName("police_office")
    val policeOffice: Any,
    @SerializedName("author")
    val author: Author,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("category_score")
    val categoryScore: Int,
    @SerializedName("score")
    val score: Int,
    @SerializedName("main_address")
    val mainAddress: String,
    @SerializedName("detail_address")
    val detailAddress: String,
    @SerializedName("latitude")
    val latitude: Float,
    @SerializedName("longitude")
    val longitude: Float,
    @SerializedName("occurred_at")
    val occurredAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
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