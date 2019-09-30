package com.seoulcontest.firstcitizen.ui.upload

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.util.FusedLocationSource
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.network.vo.NaverReverseGCResponse
import com.seoulcontest.firstcitizen.databinding.ActivityMapAddressBinding
import com.seoulcontest.firstcitizen.network.NaverReverseGCHelper
import com.seoulcontest.firstcitizen.ui.dialog.InputDetailAddressDialog
import com.seoulcontest.firstcitizen.ui.main.area.AreaFragment
import com.seoulcontest.firstcitizen.util.DetailAddressInterface
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapAddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapAddressBinding
    private lateinit var fusedLocationSource: FusedLocationSource
    private lateinit var currCameraPosition: CameraPosition
    private var currZoom = 14.0
    private var mainAddress = ""
    private var isPositionInitiated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map_address)

        initNaverMapSetting()
        initNaverMap()
        initEvent()
    }

    private fun initNaverMap() {
        fusedLocationSource = FusedLocationSource(this, AreaFragment.LOCATION_PERMISSION_REQUEST_CODE)

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_view_upload) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_view_upload, it).commit()
            }

        mapFragment.getMapAsync {
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
                    if (!isPositionInitiated) {
                        currCameraPosition =
                            CameraPosition(LatLng(location.latitude, location.longitude), currZoom)
                    }
                    isPositionInitiated = true
                }

                //카메라 이동이 완료되었을 때 불리는 콜백
                addOnCameraIdleListener {
                    currCameraPosition = it.cameraPosition
                    currZoom = it.cameraPosition.zoom
                }
            }
        }
    }

    private fun initEvent() {
        // 2019.09.27 상세 주소 받는 다이얼로그 띄우고   by Hudson
        binding.btnInputDetail.setOnClickListener {

            val latitude = currCameraPosition.target.latitude
            val longitude = currCameraPosition.target.longitude
            val position = "$longitude,$latitude"

            //현재 카메라 좌표로 주소값 가져오기
            NaverReverseGCHelper
                .getInstance()
                .apiService
                .getAddressByLngLat(
                    "9usgnvn86f",
                    "aoCLVqkHRzb3Eb5VdGd85sAHghmgfPd2RBFwWsHi",
                    position, "json", "legalcode"
                )
                .enqueue(object : Callback<NaverReverseGCResponse> {
                    override fun onFailure(call: Call<NaverReverseGCResponse>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(
                        call: Call<NaverReverseGCResponse>,
                        response: Response<NaverReverseGCResponse>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()

                            if (data != null && data.results.isNotEmpty()) {
                                with(data.results[0].region) {
                                    mainAddress = "${area1.name} ${area2.name} ${area3.name} ${area4.name}"
                                }

                                InputDetailAddressDialog(this@MapAddressActivity).apply {
                                    arguments = Bundle().apply { putString("address", mainAddress) }

                                    setOnDialogButtonClickListener(object : DetailAddressInterface {
                                        override fun onConfirmSelected(detailAddress: String) {
                                            setResult(Activity.RESULT_OK, Intent().apply {
                                                putExtra("mainAddress", mainAddress)
                                                putExtra("detailAddress", detailAddress)
                                                putExtra("latitude", latitude)
                                                putExtra("longitude", longitude)
                                            })

                                            finish()
                                        }

                                        override fun onCancelSelected() {
                                            Toast.makeText(this@MapAddressActivity, "취소되었습니다.", Toast.LENGTH_SHORT)
                                                .show()
                                        }

                                    })
                                    show(supportFragmentManager.beginTransaction(), "")
                                }
                            }
                        } else {
                            Log.d("test", response.errorBody()!!.string())
                            Toast.makeText(this@MapAddressActivity, "데이터를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
                            return
                        }
                    }
                })
        }
    }

    private fun initNaverMapSetting() {
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient("9usgnvn86f")
    }

}
