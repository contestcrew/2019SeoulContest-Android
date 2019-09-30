package com.seoulcontest.firstcitizen.util

import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.seoulcontest.firstcitizen.data.vo.Category
import com.seoulcontest.firstcitizen.data.vo.Report
import com.seoulcontest.firstcitizen.ui.infomenu.history.LeftCategoryAdapter
import com.seoulcontest.firstcitizen.ui.main.list.ListItemAdapter
import com.seoulcontest.firstcitizen.ui.upload.ImageUploadAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("setCategories")
fun ViewPager.setCategories(list: List<Category>?) {

    (adapter as? ListItemAdapter)?.run {
        setCategoryList(list)
        notifyDataSetChanged()
    }
}

@BindingAdapter("setTitle")
fun TextView.setTitle(title: String?) {
    if (!title.isNullOrEmpty()) {
        text = if (title.length <= 15) {
            title
        } else {
            title.substring(0, 15)
        }
    }
}

@BindingAdapter("setStatusTextColor")
fun TextView.setStatusTextColor(status: String?) {
    if (!status.isNullOrEmpty()) {
        when (status) {
            "도움요청중" -> setTextColor(Color.parseColor("#64dd17"))
            "진행중" -> setTextColor(Color.parseColor("#ff9800"))
        }
    }
}

@BindingAdapter("setDiffDateText")
fun TextView.setDiffDateText(data: String?) {
    if (!data.isNullOrEmpty()) {
        val requestDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(data)
        val currDate = Date()

        val dif = (currDate.time - requestDate.time) / 1000 // seconds

        val rtnText = when {
            dif < 360 -> "방금 전"
            dif < 3600 -> "${dif / 60}분 전"
            dif < 86400 -> "${dif / 3600}시간 전"
            else -> "${dif / 86400}일 전"
        }

        text = rtnText
    }
}

@BindingAdapter("setDateText")
fun TextView.setDateText(data: String?) {
    if (!data.isNullOrEmpty()) {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(data)
        text = SimpleDateFormat("yyyy-MM-dd E요일 HH:mm").format(date)
    }
}

@BindingAdapter("setImages")
fun RecyclerView.setImages(images: List<String>?) {
    (adapter as? ImageUploadAdapter)?.setStringImages(images)
}

@BindingAdapter("setReports")
fun RecyclerView.setReports(reports: List<Report>?) {
    (adapter as? LeftCategoryAdapter)?.setData(reports)
}

@BindingAdapter("setText")
fun Button.setText(select: Boolean?) {
    if (select != null) {
        text = if (select) {
            "완료"
        } else {
            "수락"
        }
    }
}