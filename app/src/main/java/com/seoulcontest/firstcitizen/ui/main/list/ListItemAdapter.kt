package com.seoulcontest.firstcitizen.ui.main.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.seoulcontest.firstcitizen.data.vo.Category

class ListItemAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var fragmentCategoryList = mutableListOf<Category>()

    fun setCategoryList(list: List<Category>?) {
        if (list != null) {
            fragmentCategoryList.clear()
            fragmentCategoryList.addAll(list)
        }
    }

    override fun getItem(position: Int): Fragment =
        ListItemFragment.newInstance(fragmentCategoryList[position].id)

    override fun getCount(): Int = fragmentCategoryList.size

    override fun getPageTitle(position: Int): CharSequence? =
        fragmentCategoryList[position].name


}