package com.seoulcontest.firstcitizen.ui.infomenu.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityHelpDetailBinding
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import com.seoulcontest.firstcitizen.viewmodel.HelpHistoryViewModel

class HelpDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelpDetailBinding
    private lateinit var lat: LatLng
    private lateinit var lng: LatLng
    private lateinit var naverMap: NaverMap
    private var category: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help_detail)
        initLoadData()
        initView()
        initEvent()
        binding.helpHistoryViewModel = HelpHistoryViewModel()
    }

    private fun initView() {
        initNaverMap()
    }

    private fun initEvent() {

        with(binding) {

            // 취소 버튼 클릭 시 해당 도움 취소 요청
            btCancel.setOnClickListener {

            }


        }

    }

    private fun initLoadData() {

        RetrofitHelper
            .getInstance()
            .apiService

    }

    private fun initNaverMap() {

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_view_help_detail) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_view_help_detail, it).commit()
            }

        // lat =
        // lng =

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


                /*val cameraUpdate = CameraUpdate
                    .toCameraPosition(
                        CameraPosition(
                            LatLng(lat, lng), 14.0
                        )
                    )
                    .animate(CameraAnimation.Easing)

                naverMap.moveCamera(cameraUpdate)
*/
                //마커 찍기
                Marker().apply {
                    //position = LatLng(lat, lng)
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
                with(binding) {
                    when (category) {

                        1 -> ivCategoryHelp.setImageResource(R.drawable.ic_restroom)
                        2 -> ivCategoryHelp.setImageResource(R.drawable.ic_loss)
                        3 -> ivCategoryHelp.setImageResource(R.drawable.ic_crash)
                        4 -> ivCategoryHelp.setImageResource(R.drawable.ic_missing)
                    }
                }

            }
        }
    }
}
