package com.seoulcontest.firstcitizen.ui.main.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.seoulcontest.firstcitizen.data.vo.Category

class ListItemAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentCategoryList = mutableListOf<Category>()
    private val fragmentList = mutableListOf<ListItemFragment>()

    fun setCategoryList(list: List<Category>?) {
        if (list != null) {
            fragmentCategoryList.clear()
            fragmentCategoryList.addAll(list)
        }
    }

    fun getFragmentByPosition(position: Int): ListItemFragment? {
        return if (fragmentList.size == 0) {
            null
        } else {
            fragmentList[position]
        }
    }

    override fun getItem(position: Int): Fragment {
        val fragment = ListItemFragment.newInstance(fragmentCategoryList[position].id)
        fragmentList.add(fragment)

        return fragment
    }

    override fun getCount(): Int = fragmentCategoryList.size

    override fun getPageTitle(position: Int): CharSequence? =
        fragmentCategoryList[position].name

}