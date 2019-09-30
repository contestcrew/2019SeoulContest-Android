package com.seoulcontest.firstcitizen.network.vo

import com.google.gson.annotations.SerializedName

data class NaverPlaceResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("places")
    val places: List<Place>,
    @SerializedName("errorMessage")
    val errorMessage: String
) {
    data class Place(
        @SerializedName("name")
        val name: String,
        @SerializedName("road_address")
        val roadAddress: String,
        @SerializedName("jibun_address")
        val jibunAddress: String,
        @SerializedName("phone_number")
        val phoneNumber: String,
        @SerializedName("x")
        val x: String,
        @SerializedName("y")
        val y: String,
        @SerializedName("distance")
        val distance: Double,
        @SerializedName("sessionId")
        val sessionId: String
    )

    data class Meta(
        @SerializedName("totalCount")
        val totalCount: Int,
        @SerializedName("count")
        val count: Int
    )
}