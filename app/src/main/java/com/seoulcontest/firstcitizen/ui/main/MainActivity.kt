package com.seoulcontest.firstcitizen.ui.main

import android.os.Bundle
import android.widget.Toast
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

    private var isCurrListFragment = false
    private var isMapOrListVisible = true

    private lateinit var pointFragment: Fragment
    private lateinit var areaFragment: Fragment
    private lateinit var listFragment: Fragment
    private lateinit var infoFragment: Fragment

    private lateinit var currFragment: Fragment

    private var mainModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2019.09.12 Main View 및 DataBinding by Hudson
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initNaverMapSetting()
        initView()
        initEvent()

        mainModel = MainViewModel()
        binding.main = mainModel
    }

    private fun initView() {
        initFragment()

    }

    private fun initFragment() {
        pointFragment = findOrCreateViewFragment(POINT_FRAGMENT_TAG)
        areaFragment = findOrCreateViewFragment(AREA_FRAGMENT_TAG)
        listFragment = findOrCreateViewFragment(LIST_FRAGMENT_TAG)
        infoFragment = findOrCreateViewFragment(INFO_FRAGMENT_TAG)

        if (!pointFragment.isAdded) {
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
        } else {
            currFragment = getCurrentFragment()
        }
    }

    private fun initEvent() {
        with(binding) {
            civPoint.setOnClickListener {

                val isLogIn = mainModel!!.isLogIn.get() ?: false

                if (isLogIn) {
                    mainModel!!.user.set(null)
                    // todo : 이 로직이 필요한가????
                    mainModel!!.isLogIn.set(false)
                    replaceFragment(POINT_FRAGMENT_TAG)
                    isMapOrListVisible = false
                } else {
                    Toast.makeText(this@MainActivity, "로그인 후 이용해주십시오.", Toast.LENGTH_SHORT).show()
                }
            }

            civMain.setOnClickListener {
                if (isMapOrListVisible) {
                    if (isCurrListFragment)
                        replaceFragment(AREA_FRAGMENT_TAG)
                    else
                        replaceFragment(LIST_FRAGMENT_TAG)

                    isCurrListFragment = !isCurrListFragment
                    binding.civMain.isSelected = isCurrListFragment
                } else {
                    if (isCurrListFragment)
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

    private fun getCurrentFragment(): Fragment {

        for (fragment in supportFragmentManager.fragments) {
            if (fragment.isVisible) {
                return fragment
            }
        }

        return findOrCreateViewFragment(AREA_FRAGMENT_TAG)
    }

    private fun replaceFragment(newFragmentTag: String) {
        val newFragment = findOrCreateViewFragment(newFragmentTag)

        supportFragmentManager.beginTransaction()
            .hide(currFragment)
            .show(newFragment)
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