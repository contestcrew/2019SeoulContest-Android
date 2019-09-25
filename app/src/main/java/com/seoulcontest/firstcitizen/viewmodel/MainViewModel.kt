package com.seoulcontest.firstcitizen.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.data.vo.Category

class MainViewModel {

    val categoryList = ObservableField<List<Category>>()
    val categoryTitleList = ObservableField<List<String>>()
    val requestList = ObservableField<List<BriefRequest>>()
    var isLogIn = ObservableBoolean()

    fun loadData(x: Float, y: Float) {
        loadCategory()
        loadBriefRequests(x, y)
    }

    private fun loadCategory() {
        //서버랑 통신해서 데이터 가져오기
        //미구현
        val categories = listOf(Category("똥휴지"), Category("뺑소니"), Category("분실"), Category("접촉사고"))
        categoryList.set(categories)
        categoryTitleList.set(categories.map { it.category })
    }

    private fun loadBriefRequests(x: Float, y: Float) {

        val coordiX = 37.5405655f
        val coordiY = 127.0695086f

        //서버랑 통신해서 데이터 가져오기
        //미구현
        val data = listOf(
            BriefRequest("똥휴지", 1, arrayOf(coordiX, coordiY), ""),
            BriefRequest("뺑소니", 2, arrayOf(coordiX + 0.001f, coordiY), ""),
            BriefRequest("분실", 3, arrayOf(coordiX + 0.002f, coordiY), ""),
            BriefRequest("접촉사고", 4, arrayOf(coordiX + 0.003f, coordiY), "")
        )

        requestList.set(data)

    }

    companion object {
        private var INSTANCE: MainViewModel? = null

        fun getInstance() = INSTANCE ?: MainViewModel().apply {
            INSTANCE = this
        }
    }

}