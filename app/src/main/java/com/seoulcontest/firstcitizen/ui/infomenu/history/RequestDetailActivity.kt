package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityRequestDetailBinding
import com.seoulcontest.firstcitizen.ui.upload.ImageUploadAdapter
import com.seoulcontest.firstcitizen.viewmodel.RequestDetailViewModel

class RequestDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestDetailBinding
    private val viewModel by lazy {
        RequestDetailViewModel().apply {
            loadData(intent.getIntExtra("id", 0))
        }
    }
    private var category: Int? = 0

    private lateinit var naverMap: NaverMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_request_detail)
        binding.viewModel = viewModel

        if (intent != null) {
            category = intent.getIntExtra("category", 0)
        }

        initView()
    }

    private fun initView() {
        initRecyclerView()
        initNaverMap()
    }

    private fun initRecyclerView() {
        binding.rvDetail.adapter = ImageUploadAdapter(this)
        if (category == 1) {
            binding.rvHelp.adapter = PooCategoryAdapter()
        } else {
            binding.rvHelp.adapter = LeftCategoryAdapter()
        }
    }

    private fun initNaverMap() {
        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_view_detail) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_view_detail, it).commit()
            }

        val lat = intent.getFloatExtra("lat", 0.0f).toDouble()
        val lng = intent.getFloatExtra("lng", 0.0f).toDouble()

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
