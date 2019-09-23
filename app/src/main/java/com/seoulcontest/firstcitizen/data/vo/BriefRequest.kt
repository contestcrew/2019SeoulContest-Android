package com.seoulcontest.firstcitizen.data.vo

data class BriefRequest(
    val category: String = "",
    val id: Int = 0,
    val coordinate: Array<Float>,
    val uploadTime: String = "",
    val servicePoint: Int = 0,
    val userPoint: Int = 0,
    val title: String = "",
    val contents: String = "",
    val processing: String = ""

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BriefRequest

        if (!coordinate.contentEquals(other.coordinate)) return false

        return true
    }

    override fun hashCode(): Int {
        return coordinate.contentHashCode()
    }
}