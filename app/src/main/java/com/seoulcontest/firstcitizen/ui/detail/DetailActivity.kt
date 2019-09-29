package com.seoulcontest.firstcitizen.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityDetailBinding
import com.seoulcontest.firstcitizen.ui.dialog.HelpDialog
import com.seoulcontest.firstcitizen.ui.help.HelpUploadActivity
import com.seoulcontest.firstcitizen.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel by lazy { DetailViewModel() }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var naverMap: NaverMap
    private var category: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        initViewModel()
        initView()
        initEvent()
    }

    private fun initViewModel() {
        binding.viewModel = detailViewModel.apply {
            loadData(intent.getIntExtra("id", 0))
        }
    }

    private fun initView() {
        initNaverMap()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        //미구현
    }

    private fun initEvent() {
        binding.btHelp.setOnClickListener {

            val isLogin = intent.getBooleanExtra("isLogin", false)

            // 로그인 되어 있는 상태이다면
            if (isLogin) {
                // 2019.09.29 카테고리에 따라 분기(다이얼로그, HelpUploadActivity) by Hudson
                when (category) {

                    0 -> {
                        return@setOnClickListener
                    }
                    // 똥휴지의 경우 다이얼로그 띄어주기
                    1 -> {
                        HelpDialog().show(supportFragmentManager.beginTransaction(), "")
                    }
                    // 그 외 경우 HelpUploadActivity 로 이동
                    else -> {
                        startActivity(Intent(this, HelpUploadActivity::class.java))
                    }
                }
                // 로그인 되어 있지 않다면
            } else {
                Toast.makeText(this,"로그인 후 이용해주십시오.",Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun initNaverMap() {

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_view_detail) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_view_detail, it).commit()
            }

        //NaverMap 객체가 준비되면 람다 코드가 실행됨.
        mapFragment.getMapAsync {
            naverMap = it

            with(it) {
                mapType = NaverMap.MapType.Basic
                isIndoorEnabled = true
                minZoom = 15.0

                with(uiSettings) {
                    isCompassEnabled = false
                    isLocationButtonEnabled = false
                    isZoomControlEnabled = false
                    isScaleBarEnabled = false
                    isLocationButtonEnabled = false
                }

                //카메라 제어
                val lat = intent.getFloatExtra("lat", 0.0f).toDouble()
                val lng = intent.getFloatExtra("lng", 0.0f).toDouble()
                category = intent.getIntExtra("category", 0)

                val cameraUpdate = CameraUpdate
                    .toCameraPosition(
                        CameraPosition(
                            LatLng(lat, lng), 14.0
                        )
                    )
                    .animate(CameraAnimation.Easing)

                naverMap.moveCamera(cameraUpdate)

                //마커 찍기
                Marker().apply {
                    position = LatLng(lat, lng)
                    width = 150
                    height = 200

                    when (category) {
                        1 -> icon = OverlayImage.fromResource(R.drawable.pin_restroom)
                        2 -> icon = OverlayImage.fromResource(R.drawable.pin_loss)
                        3 -> icon = OverlayImage.fromResource(R.drawable.pin_crash)
                        4 -> icon = OverlayImage.fromResource(R.drawable.pin_missing)
                    }

                    map = naverMap
                }

                //카테고리별로 이미지뷰 세팅
                when (category) {
                    1 -> binding.ivCategory.setImageResource(R.drawable.ic_restroom)
                    2 -> binding.ivCategory.setImageResource(R.drawable.ic_loss)
                    3 -> binding.ivCategory.setImageResource(R.drawable.ic_crash)
                    4 -> binding.ivCategory.setImageResource(R.drawable.ic_missing)
                }
            }
        }
    }
}
