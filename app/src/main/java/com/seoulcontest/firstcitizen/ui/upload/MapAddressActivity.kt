package com.seoulcontest.firstcitizen.ui.upload

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.naver.maps.map.NaverMapSdk
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityMapAddressBinding
import com.seoulcontest.firstcitizen.ui.dialog.InputDetailAddressDialog
import com.seoulcontest.firstcitizen.util.DetailAddressInterface

class MapAddressActivity : AppCompatActivity(), MapAddressFragment.OnFragmentInteractionListener {

    lateinit var binding: ActivityMapAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map_address)
        initNaverMapSetting()
        initView()
        initEvent()
    }

    private fun initEvent() {
        // 2019.09.27 상세 주소 받는 다이얼로그 띄우고   by Hudson
        binding.btnInputDetail.setOnClickListener {

            InputDetailAddressDialog(this).apply {

                setOnDialogButtonClickListener(object : DetailAddressInterface {
                    override fun onConfirmSelected(result: String) {
                        // todo : 현재 상세 주소값이 넘어옴, mainAddress 값 얻어야 함
                        setResult(Activity.RESULT_OK, Intent().apply {
                            putExtra("mainAddress", "")
                            putExtra("detailAddress", result)
                        })
                    }

                    override fun onCancelSelected() {
                        Toast.makeText(this@MapAddressActivity, "취소되었습니다.", Toast.LENGTH_SHORT).show()
                    }

                })
                show(supportFragmentManager.beginTransaction(), "")
            }
        }
    }

    private fun initNaverMapSetting() {
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient("9usgnvn86f")
    }

    private fun initView() {

    }

    override fun onFragmentInteraction(address: String) {

    }
}
