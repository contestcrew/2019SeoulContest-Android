package com.seoulcontest.firstcitizen.ui.main.area

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.databinding.FragmentAreaBinding
import com.seoulcontest.firstcitizen.ui.detail.DetailActivity
import com.seoulcontest.firstcitizen.ui.dialog.CategoryDialog
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class AreaFragment : Fragment() {
    private val viewModel = MainViewModel.getInstance()

    private lateinit var binding: FragmentAreaBinding
    private lateinit var fusedLocationSource: FusedLocationSource
    private lateinit var currPosition: CameraPosition

    private var dataCalledPosition = CameraPosition(LatLng(35.0, 120.0), 14.0)
    private val markerList = mutableListOf<Marker>()
    private var naverMap: NaverMap? = null
    private var currZoom = 14.0

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
        initCallback()
        checkPermission()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel.apply {
            loadCategoryList()
        }
    }

    private fun initView() {
        initNaverMap()
    }

    private fun initEvent() {
        binding.cardview.setOnClickListener {
            startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra("id", viewModel.currRequest.get()?.id)
                putExtra("lat", viewModel.currRequest.get()?.latitude)
                putExtra("lng", viewModel.currRequest.get()?.longitude)
                putExtra("category", viewModel.currRequest.get()?.category)
            })
        }

        binding.btLocation.setOnClickListener {
            //카메라 현재위치로 이동
            naverMap?.let {
                it.moveCamera(
                    CameraUpdate
                        .toCameraPosition(currPosition)
                        .animate(CameraAnimation.Easing)
                )
            }
        }

        binding.btRequest.setOnClickListener {
            CategoryDialog(requireContext()).show(
                requireActivity().supportFragmentManager.beginTransaction(), ""
            )
        }

        binding.btRefresh.setOnClickListener {
            viewModel.loadBriefRequestList(
                currPosition.target.latitude.toFloat(),
                currPosition.target.longitude.toFloat()
            )

            dataCalledPosition =
                CameraPosition(LatLng(currPosition.target.latitude, currPosition.target.longitude), currZoom)
            //카메라 현재위치로 이동
            naverMap?.let {
                it.moveCamera(
                    CameraUpdate
                        .toCameraPosition(currPosition)
                        .animate(CameraAnimation.Easing)
                )
            }
        }
    }

    private fun initNaverMap() {
        fusedLocationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_view_detail) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_view_detail, it).commit()
            }

        //NaverMap 객체가 준비되면 람다 코드가 실행됨.
        mapFragment.getMapAsync {
            naverMap = it

            with(it) {
                with(uiSettings) {
                    isCompassEnabled = false
                    isLocationButtonEnabled = false
                    isZoomControlEnabled = false
                    isScaleBarEnabled = false
                    isLocationButtonEnabled = false
                    isLogoClickEnabled = false
                    logoGravity = Gravity.TOP
                    setLogoMargin(48, 48, 0, 0)
                }

                mapType = NaverMap.MapType.Basic
                isIndoorEnabled = true
                locationSource = fusedLocationSource
                locationTrackingMode = LocationTrackingMode.Follow
                minZoom = 15.0

                //현재 사용자 위치 이벤트 콜백
                addOnLocationChangeListener { location ->
                    currPosition =
                        CameraPosition(LatLng(location.latitude, location.longitude), currZoom)

                    Log.d("test", "${location.latitude} -  ${location.longitude}")

                    val distance = getDistanceOfTwoLatLng(
                        currPosition.target.latitude,
                        currPosition.target.longitude,
                        dataCalledPosition.target.latitude,
                        dataCalledPosition.target.longitude
                    )

                    //데이터를 호출하고 100m 이상 이동했을 때 다시 데이터 불러오기
                    if (distance > 100) {
                        viewModel.loadBriefRequestList(
                            currPosition.target.latitude.toFloat(),
                            currPosition.target.longitude.toFloat()
                        )

                        dataCalledPosition =
                            CameraPosition(LatLng(location.latitude, location.longitude), currZoom)
                    }
                }

                addOnCameraIdleListener {
                    currZoom = it.cameraPosition.zoom
                }
            }
        }
    }

    private fun initCallback() {
        viewModel.briefRequestList.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                markerList.map {
                    it.map = null // 마커 지우기
                }
                markerList.clear()

                (sender as ObservableField<List<BriefRequest>>).get()?.let { requestList ->
                    for (briefRequest in requestList) {
                        val marker = Marker().apply {
                            position =
                                LatLng(
                                    briefRequest.latitude.toDouble(),
                                    briefRequest.longitude.toDouble()
                                )

                            width = 150
                            height = 200

                            when (briefRequest.category) {
                                1 -> icon = OverlayImage.fromResource(R.drawable.pin_restroom)
                                2 -> icon = OverlayImage.fromResource(R.drawable.pin_loss)
                                3 -> icon = OverlayImage.fromResource(R.drawable.pin_crash)
                                4 -> icon = OverlayImage.fromResource(R.drawable.pin_missing)
                            }

                            map = naverMap

                            //마커 클릭시 이벤트 처리
                            setOnClickListener {
                                viewModel.currRequest.set(briefRequest)

                                //카테고리별로 이미지뷰 세팅
                                when (briefRequest.category) {
                                    1 -> binding.ivCategory.setImageResource(R.drawable.ic_restroom)
                                    2 -> binding.ivCategory.setImageResource(R.drawable.ic_loss)
                                    3 -> binding.ivCategory.setImageResource(R.drawable.ic_crash)
                                    4 -> binding.ivCategory.setImageResource(R.drawable.ic_missing)
                                }

                                //카메라 마커위치로 이동
                                val cameraUpdate = CameraUpdate
                                    .toCameraPosition(
                                        CameraPosition(
                                            LatLng(
                                                briefRequest.latitude.toDouble(),
                                                briefRequest.longitude.toDouble()
                                            ), currZoom
                                        )
                                    )
                                    .animate(CameraAnimation.Easing)

                                naverMap?.let { it.moveCamera(cameraUpdate) }
                                viewModel.isMarkerClicked.set(true)

                                true //true 일 경우 위 이벤트 실행
                            }
                        }

                        markerList.add(marker)
                    }
                }
            }
        })
    }

    private fun getDistanceOfTwoLatLng(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {  // generally used geo measurement function
        var R = 6378.137 // Radius of earth in KM
        var dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180
        var dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180
        var a = sin(dLat / 2) * sin(dLat / 2) +
                cos(lat1 * Math.PI / 180) * cos(lat2 * Math.PI / 180) *
                sin(dLon / 2) * sin(dLon / 2)
        var c = 2 * atan2(sqrt(a), sqrt(1 - a))
        var d = R * c
        return d * 1000 // meters
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (fusedLocationSource.onRequestPermissionsResult(requestCode, permissions, grantResults))
            return

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }

}