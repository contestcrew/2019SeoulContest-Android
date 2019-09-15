package com.seoulcontest.firstcitizen.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentMapBinding

class AreaFragment : Fragment() {

    lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initMap()
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

                with(uiSettings) {
                    isZoomControlEnabled = false
                    isScaleBarEnabled = false
                    isLocationButtonEnabled = false
                }

                binding.btLocation.map = it
            }
        }
    }

}
