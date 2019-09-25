package com.seoulcontest.firstcitizen.ui.main.area

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentAreaBinding
import com.seoulcontest.firstcitizen.ui.detail.DetailActivity
import com.seoulcontest.firstcitizen.ui.dialog.CategoryDialog
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
import java.util.ArrayList

class AreaFragment : Fragment() {

    private val viewModel = MainViewModel.getInstance()

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

        initViewModel()
        initView()
        initEvent()
        checkPermission()
        viewModel.loadData(0.toFloat(), 0.toFloat())
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
    }

    private fun initView() {
        initNaverMap()
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

    private fun initNaverMap() {
        fusedLocationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_view) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_view, it).commit()
            }

        //NaverMap 객체가 준비되면 람다 코드가 실행됨.
        mapFragment.getMapAsync {
            naverMap = it

            loadMarker()

            with(it) {
                mapType = NaverMap.MapType.Basic
                isIndoorEnabled = true
                locationSource = fusedLocationSource
                locationTrackingMode = LocationTrackingMode.Face

                //현재 사용자 위치 이벤트 콜백
                addOnLocationChangeListener { location ->
                    currPosition =
                        CameraPosition(LatLng(location.latitude, location.longitude), 14.0)
                }

                //현재 카메라 위치 이벤트 콜백
//                addOnCameraChangeListener { reason, animated ->
//                    val cameraPosition = naverMap.cameraPosition
//
//                    //사용자가 직접 카메라를 움직였을 때만
//                    //현재 카메라 위치에서 데이터 불러오기
//                    if (reason == REASON_GESTURE) {
//                        viewModel.loadData(
//                            cameraPosition.target.latitude.toFloat(),
//                            cameraPosition.target.longitude.toFloat()
//                        )
//
//                        Log.d("test" , "moved")
//                    }
//                }

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

    private fun loadMarker() {
        val requestList = viewModel.requestList.get()

        if (requestList != null) {
            for (request in requestList) {
                Marker().apply {
                    position =
                        LatLng(request.coordinate[0].toDouble(), request.coordinate[1].toDouble())
                    map = naverMap
                }
            }
        }
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

    // Naver 맵 이외의 위험 권한 체크하는 함수
    private fun checkPermission() {

        val permissionListener = object : PermissionListener {

            override fun onPermissionGranted() {

            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

            }
        }

        TedPermission.with(context)
            .setPermissionListener(permissionListener)
            .setDeniedMessage(getString(R.string.permission_denied_message))
            .setPermissions(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ).check()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}