package com.seoulcontest.firstcitizen.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.naver.maps.map.NaverMapSdk
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityMainBinding
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var isMap = true
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
            .add(R.id.container, pointFragment)
            .add(R.id.container, areaFragment)
            .add(R.id.container, listFragment)
            .add(R.id.container, infoFragment)
            .hide(pointFragment)
            .hide(listFragment)
            .hide(infoFragment)
            .show(areaFragment)
            .commit()

        currFragment = areaFragment
    }

    private fun initEvent() {
        with(binding) {
            fabPoint.setOnClickListener {
                replaceFragment(pointFragment)
                isMapOrListVisible = false
            }

            fabMain.setOnClickListener {
                if (isMapOrListVisible) {
                    if (isMap)
                        replaceFragment(listFragment)
                    else
                        replaceFragment(areaFragment)

                    isMap = !isMap
                } else {
                    if (isMap)
                        replaceFragment(areaFragment)
                    else
                        replaceFragment(listFragment)

                    isMapOrListVisible = true
                }
            }

            fabInformation.setOnClickListener {
                replaceFragment(infoFragment)
                isMapOrListVisible = false
            }
        }
    }

    private fun replaceFragment(newFragment: Fragment) {
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
}