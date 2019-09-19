package com.seoulcontest.firstcitizen.ui.main.area

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentAreaBinding
import com.seoulcontest.firstcitizen.ui.detail.DetailActivity
import com.seoulcontest.firstcitizen.ui.dialog.CategoryDialog
import kotlin.String

class AreaFragment : Fragment() {

    private lateinit var binding: FragmentAreaBinding
    private lateinit var fusedLocationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap

    lateinit var currPosition: CameraPosition

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
        fusedLocationSource = FusedLocationSource(this,
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun initEvent() {
        binding.cardview.setOnClickListener {
            startActivity(Intent(context, DetailActivity::class.java))
        }

        binding.btLocation.setOnClickListener {
            val cameraUpdate = CameraUpdate
                .toCameraPosition(currPosition)
                .animate(CameraAnimation.Easing)

            naverMap.moveCamera(cameraUpdate)
        }

        binding.btRequest.setOnClickListener {
            val dialog =
                CategoryDialog(activity!!.applicationContext)

            dialog.show(activity!!.supportFragmentManager.beginTransaction(), "")
        }
    }

    private fun initMap() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_view) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_view, it).commit()
            }

        //NaverMap 객체가 준비되면 람다 코드가 실행됨.
        mapFragment.getMapAsync {
            naverMap = it

            with(it) {
                mapType = NaverMap.MapType.Basic
                isIndoorEnabled = true
                locationSource = fusedLocationSource
                locationTrackingMode = LocationTrackingMode.Face

                addOnLocationChangeListener { location ->
                    currPosition =
                        CameraPosition(LatLng(location.latitude, location.longitude), 14.0)
                }


                with(uiSettings) {
                    isCompassEnabled = false
                    isLocationButtonEnabled = false
                    isZoomControlEnabled = false
                    isScaleBarEnabled = false
                    isLocationButtonEnabled = false
                }

            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}