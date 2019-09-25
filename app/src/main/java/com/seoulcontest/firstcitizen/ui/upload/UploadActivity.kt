package com.seoulcontest.firstcitizen.ui.upload

import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityUploadBinding
import com.seoulcontest.firstcitizen.ui.dialog.PolicePickerDialog
import com.seoulcontest.firstcitizen.util.HorizontalSpacingItemDecoration
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType
import java.util.*

class UploadActivity : AppCompatActivity(), NumberPicker.OnValueChangeListener {

    lateinit var binding: ActivityUploadBinding
    lateinit var policeArr: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload)

        // 2019.09.16 UploadActivity 초기화 및 클릭 이벤트 처리 by Hudson
        initView()
        initEvent()

    }


    private fun initView() {
        // 넘어온 카테고리에 해당하는 텍스트 적용
        val category = intent.getStringExtra("binding")
        binding.tvCategory.text = category
    }

    private fun initEvent() {

        // 2019.09.24 관할 경찰서 입력 버튼 클릭 시 넘버 피커 보여주기 by Hudson
        binding.btnAddPolice.setOnClickListener {
            showNumberPicker()
        }

        // 2019.09.24 첨부 파일 버튼 클릭 시 앨범으로 이동 by Hudson
        binding.btnSelectFile.setOnClickListener {
            goToAlbum()
        }


        // 발생 시간 입력 버튼 클릭 시 타임 피커 보여주기
        binding.btnOccurredTime.setOnClickListener {
            showTimePicker()
        }
    }

    private fun showTimePicker() {

        // todo : 시간을 float 단위로 넘길 것

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(
            this, TimePickerDialog.OnTimeSetListener(function = { view, hour, minute ->

                val occuredTime = String.format("$year 년 $month 월 $day 일 $hour 시 $minute 분")
                binding.btnOccurredTime.text = occuredTime


            }), hour, minute, false
        )

        timePicker.show()
    }

    // 사용자가 선택한 경찰서 표시
    override fun onValueChange(numberPicker: NumberPicker?, default: Int, changed: Int) {

        binding.btnAddPolice.text = policeArr[changed]

    }

    private fun showNumberPicker() {

        policeArr = resources.getStringArray(R.array.police_name)

        val numberPickerDialog = PolicePickerDialog(this, policeArr)
        numberPickerDialog.setValueChangedListener(this)
        numberPickerDialog.show(supportFragmentManager, "time picker")

    }

    private fun goToAlbum() {

        //val albumIntent = Intent()

        // 이미지 다중 선택
        //albumIntent.type = "image/*"
        //albumIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        //albumIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        //albumIntent.action = Intent.ACTION_PICK

        // startActivityForResult(Intent.createChooser(albumIntent, "이미지를 가져올 앨범을 선택해주세요."), RC_SELECT_FILES)

        TedImagePicker.with(this).mediaType(MediaType.IMAGE).startMultiImage { uriList ->
            setFiles(uriList)
        }
    }

    private fun setFiles(uriList: List<Uri>) {

        // 리사이클러 뷰
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRequestFileList.layoutManager = layoutManager

        binding.rvRequestFileList.adapter = UploadAdapter(applicationContext, uriList)
        // recyclerView 아이템에 각각 스페이싱
        binding.rvRequestFileList.addItemDecoration(HorizontalSpacingItemDecoration(64))
    }
}
