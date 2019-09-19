package com.seoulcontest.firstcitizen.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ListAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentCategoryList = listOf("전체", "똥휴지", "뺑소니", "분실", "접촉사고")

    override fun getItem(position: Int): Fragment {
        return CategoryFragment.newInstance(fragmentCategoryList[position])
    }

    override fun getCount(): Int = fragmentCategoryList.size

    override fun getPageTitle(position: Int): CharSequence? = fragmentCategoryList[position]

}