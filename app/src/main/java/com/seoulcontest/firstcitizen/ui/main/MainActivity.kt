package com.seoulcontest.firstcitizen.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.naver.maps.map.NaverMapSdk
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityMainBinding
import com.seoulcontest.firstcitizen.ui.main.area.AreaFragment
import com.seoulcontest.firstcitizen.ui.main.info.InfoFragment
import com.seoulcontest.firstcitizen.ui.main.list.ListFragment
import com.seoulcontest.firstcitizen.ui.main.point.PointFragment
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var isCurrList = false
    private var isMapOrListVisible = true

    private val pointFragment = PointFragment()
    private val areaFragment = AreaFragment()
    private val listFragment = ListFragment()
    private val infoFragment = InfoFragment()
    private lateinit var currFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2019.09.12 Main View 및 DataBinding by Hudson
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        initNaverMapSetting()
        initView()
        initEvent()

        val mainModel = MainViewModel("Test Text")
        binding.main = mainModel
    }

    private fun initView() {
        initFragment()

    }

    private fun initFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, pointFragment, POINT_FRAGMENT_TAG)
            .add(R.id.container, areaFragment, AREA_FRAGMENT_TAG)
            .add(R.id.container, listFragment, LIST_FRAGMENT_TAG)
            .add(R.id.container, infoFragment, INFO_FRAGMENT_TAG)
            .hide(pointFragment)
            .hide(listFragment)
            .hide(infoFragment)
            .show(areaFragment)
            .commit()

        currFragment = areaFragment
    }

    private fun initEvent() {
        with(binding) {
            civPoint.setOnClickListener {
                replaceFragment(POINT_FRAGMENT_TAG)
                isMapOrListVisible = false
            }

            civMain.setOnClickListener {
                if (isMapOrListVisible) {
                    if (isCurrList)
                        replaceFragment(AREA_FRAGMENT_TAG)
                    else
                        replaceFragment(LIST_FRAGMENT_TAG)

                    isCurrList = !isCurrList
                    binding.civMain.isSelected = isCurrList
                } else {
                    if (isCurrList)
                        replaceFragment(LIST_FRAGMENT_TAG)
                    else
                        replaceFragment(AREA_FRAGMENT_TAG)

                    isMapOrListVisible = true
                }
            }

            civInfo.setOnClickListener {
                replaceFragment(INFO_FRAGMENT_TAG)
                isMapOrListVisible = false
            }
        }
    }

    private fun findOrCreateViewFragment(newFragmentTag: String): Fragment {
        var newFragment = supportFragmentManager.findFragmentByTag(newFragmentTag)

        if (newFragment == null) {
            when (newFragmentTag) {
                POINT_FRAGMENT_TAG -> newFragment =
                    PointFragment()
                AREA_FRAGMENT_TAG -> newFragment =
                    AreaFragment()
                LIST_FRAGMENT_TAG -> newFragment =
                    ListFragment()
                INFO_FRAGMENT_TAG -> newFragment =
                    InfoFragment()
            }
        }

        return newFragment!!
    }

    private fun replaceFragment(newFragmentTag: String) {
        val newFragment = findOrCreateViewFragment(newFragmentTag)

        supportFragmentManager.beginTransaction()
            .hide(currFragment)
            .show(newFragment)
            .addToBackStack(null)
            .commit()

        currFragment = newFragment
    }

    private fun initNaverMapSetting() {
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient("9usgnvn86f")
    }

    companion object {
        const val POINT_FRAGMENT_TAG = "point"
        const val AREA_FRAGMENT_TAG = "area"
        const val LIST_FRAGMENT_TAG = "list"
        const val INFO_FRAGMENT_TAG = "info"
    }
}