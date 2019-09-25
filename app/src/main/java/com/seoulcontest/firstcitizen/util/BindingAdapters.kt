package com.seoulcontest.firstcitizen.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.seoulcontest.firstcitizen.ui.main.list.ListItemAdapter

@BindingAdapter("setTitles")
fun ViewPager.setTitles(titles: List<String>?) {

    (adapter as? ListItemAdapter)?.run {
        setCategoryList(titles)
        notifyDataSetChanged()
    }

}