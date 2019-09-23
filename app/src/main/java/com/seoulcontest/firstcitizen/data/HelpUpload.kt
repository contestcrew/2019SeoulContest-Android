package com.seoulcontest.firstcitizen.data

data class HelpUpload(
    val id: Int,
    val uploadUserId: String,
    val uploadTime: String,
    val content: String,
    val upload: String
)