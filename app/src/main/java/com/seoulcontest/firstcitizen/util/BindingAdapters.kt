package com.seoulcontest.firstcitizen.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.seoulcontest.firstcitizen.data.vo.Category
import com.seoulcontest.firstcitizen.ui.main.list.ListItemAdapter

@BindingAdapter("setCategories")
fun ViewPager.setCategories(list: List<Category>?) {

    (adapter as? ListItemAdapter)?.run {
        setCategoryList(list)
        notifyDataSetChanged()
    }

}