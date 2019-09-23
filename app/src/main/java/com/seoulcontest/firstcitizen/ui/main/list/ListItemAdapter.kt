package com.seoulcontest.firstcitizen.ui.main.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ListItemAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var fragmentCategoryTitleList = mutableListOf<String>()

    fun setCategoryList(list: List<String>?) {
        if (list != null) {
            fragmentCategoryTitleList.clear()
            fragmentCategoryTitleList.addAll(list)
        }
    }

    override fun getItem(position: Int): Fragment {
        return ListItemFragment.newInstance(fragmentCategoryTitleList[position])
    }

    override fun getCount(): Int = fragmentCategoryTitleList.size

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentCategoryTitleList[position]
    }

}