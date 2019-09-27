package com.seoulcontest.firstcitizen.util

import android.content.Context

class ItemResizer(val context: Context) {

    private val display = context.resources.displayMetrics

    // 디스플레이 전체 크기의 8할에 해당하는 값을 리턴
    fun getDisplayWidth(): Int = (display.widthPixels * 0.8).toInt()

    // getDisplayWidth()의 4 : 3 비율의 높이 값을 구해 리턴 , 사진 4 : 3 비율을 맞추기 위함
    fun getDisplayHeight(): Int = (getDisplayWidth() * 3) / 4
}