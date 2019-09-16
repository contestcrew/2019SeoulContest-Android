package com.seoulcontest.firstcitizen.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.util.FusedLocationSource
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentAreaBinding
import com.seoulcontest.firstcitizen.ui.detail.DetailActivity

class AreaFragment : Fragment() {

    private lateinit var binding: FragmentAreaBinding
    private lateinit var fusedLocationSource: FusedLocationSource

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_area, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        initEvent()
        initMap()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (fusedLocationSource.onRequestPermissionsResult(requestCode, permissions, grantResults))
            return

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun initView() {
        initMap()
        fusedLocationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
    }

    private fun initEvent() {
        binding.cardview.setOnClickListener {
            startActivity(Intent(context, DetailActivity::class.java))
        }
    }

    private fun initMap() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }

        //NaverMap 객체가 준비되면 람다 코드가 실행됨.
        mapFragment.getMapAsync {
            with(it) {
                mapType = NaverMap.MapType.Basic
                isIndoorEnabled = true
                locationTrackingMode = LocationTrackingMode.Face
                locationSource = fusedLocationSource

                with(uiSettings) {
                    isZoomControlEnabled = false
                    isScaleBarEnabled = false
                    isLocationButtonEnabled = false
                }

                binding.btLocation.map = it
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}