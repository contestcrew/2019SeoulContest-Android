package com.seoulcontest.firstcitizen.viewmodel

import androidx.databinding.ObservableField
import com.seoulcontest.firstcitizen.data.vo.GetReportHistory

class HelpHistoryViewModel {

    var reportHistory = ObservableField<GetReportHistory>() // 제보 히스토리 정보를 가지고 있는 변수


    companion object {
        private var INSTANCE: HelpHistoryViewModel? = null

        fun getInstance() = INSTANCE ?: HelpHistoryViewModel().apply {
            INSTANCE = this
        }
    }
}