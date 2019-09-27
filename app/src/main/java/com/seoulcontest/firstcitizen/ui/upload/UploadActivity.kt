package com.seoulcontest.firstcitizen.ui.upload

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.Request
import com.seoulcontest.firstcitizen.databinding.ActivityUploadBinding
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import com.seoulcontest.firstcitizen.ui.dialog.PolicePickerDialog
import com.seoulcontest.firstcitizen.util.HorizontalSpacingItemDecoration
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.*

class UploadActivity : AppCompatActivity(), NumberPicker.OnValueChangeListener {

    lateinit var binding: ActivityUploadBinding
    // 경찰서 이름을 가지고 있는 Array
    private lateinit var policeArr: Array<String>
    // 지도로 찾기 요청코드
    private val RC_MAP_RESULT = 0
    // 주소로 찾기 요청코드
    private val RC_TEXT_ADDRESS_RESULT = 1
    // Request 객체
    lateinit var newRequest: Request
    // Request 필수값(카테고리, 제목, 내용)
    var category = 0
    lateinit var title: String
    lateinit var content: String
    // Request 선택값
    private var policeOffice = 0
    private var status: String? = null
    var categoryScore = 0
    var score = 0
    var body = mutableListOf<MultipartBody.Part>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload)

        // 2019.09.16 UploadActivity 초기화 및 클릭 이벤트 처리 by Hudson
        initView()
        initEvent()
    }


    private fun initView() {
        // 2019.09.16 넘어온 카테고리 포지션에 해당하는 텍스트 적용 by Hudson
        // 2019.09.27 String값에서 Int값으로 변경(position + 1) 값이 넘어옴 by Hudson
        category = intent.getIntExtra("selectedCategory", category)
        with(binding.tvCategory) {
            when (category) {
                1 -> text = "똥휴지"
                2 -> text = "분실"
                3 -> text = "접촉사고"
                4 -> text = "실종"
                else -> "잘못된 접근"
            }
        }

    }


    private fun initEvent() {

        with(binding) {
            // 2019.09.24 관할 경찰서 입력 버튼 클릭 시 넘버 피커 보여주기 by Hudson
            btnAddPolice.setOnClickListener {
                showNumberPicker()
            }

            // 2019.09.24 첨부 파일 버튼 클릭 시 앨범으로 이동 by Hudson
            btnSelectFile.setOnClickListener {
                goToAlbum()
            }

            // 발생 시간 입력 버튼 클릭 시 타임 피커 보여주기
            btnOccurredTime.setOnClickListener {
                showTimePicker()
            }
            // 2019.09.27 맵으로 이동하는 로직 by Hudson
            btnFindByArea.setOnClickListener {
                startActivityForResult(
                    Intent(this@UploadActivity, MapAddressActivity::class.java), RC_MAP_RESULT
                )
            }

            // 2019.09.27 상세 주소 입력하는 액티비티로 이동 by Hudson
            btnFindByAddress.setOnClickListener {
                startActivityForResult(
                    Intent(this@UploadActivity, DetailAddressActivity::class.java), RC_TEXT_ADDRESS_RESULT
                )
            }

            //2019.09.27 필수값 입력이 되었으면 Request 통신을 요청하는 로직 by Hudson
            btnRequest.setOnClickListener {

                title = edtTitle.text.toString().trim()
                content = edtMessage.text.toString().trim()

                // Request 필수값 체크
                if (title != "" && content != "" && category != 0) {
                    createNewRequest()

                    // 예외 발생 안내 처리
                } else if (title == "" || content == "") {
                    Toast.makeText(this@UploadActivity, "제목이나 내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // 새로운 요청 생성하는 로직
    private fun createNewRequest() {

        val categoryBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), category.toString())
        val contentBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), content)
        val titleBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), title)
        val latitudeBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), (37.537F).toString())
        val longitudeBody = RequestBody.create(okhttp3.MediaType.parse("text/plain"), (127.064F).toString())

        RetrofitHelper
            .getInstance()
            .apiService
            .createRequest(
                "Token b5b31df2f888844ae367ccae23ae154575e5dae9",
                categoryBody,
                contentBody,
                titleBody,
                latitudeBody,
                longitudeBody,
                body
            )
            .enqueue(object : Callback<Request> {
                override fun onFailure(call: Call<Request>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<Request>, response: Response<Request>) {


                    if (response.isSuccessful) {
                        Log.d("files", Gson().toJson(response.body()))

                    } else {
                        Log.d("files", "???  ${response.errorBody()}")
                        Log.d("files", "???  ${Gson().toJson(response.body())}")

                    }
                }
            })
    }

    private fun showTimePicker() {

        // todo : 시간을 string 타입으로 넘길 것
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            this, TimePickerDialog.OnTimeSetListener(
                function = { _, hour, minute ->
                    binding.btnOccurredTime.text = String.format("$year 년 $month 월 $day 일 $hour 시 $minute 분")
                }), hour, minute, false
        ).show()
    }

    // 사용자가 선택한 경찰서 표시
    override fun onValueChange(numberPicker: NumberPicker?, default: Int, changed: Int) {
        binding.btnAddPolice.text = policeArr[changed]
        policeOffice = changed
    }


    private fun showNumberPicker() {

        policeArr = resources.getStringArray(R.array.police_name)

        PolicePickerDialog(policeArr).apply {
            setValueChangedListener(this@UploadActivity)
            show(supportFragmentManager, "time picker")
        }
    }

    private fun goToAlbum() {

        TedImagePicker.with(this).mediaType(MediaType.IMAGE).startMultiImage { uriList ->
            setImagess(uriList)
        }
    }

    private fun setImagess(uriList: List<Uri>) {

        // 리사이클러 뷰
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

        with(binding) {
            rvRequestFileList.layoutManager = layoutManager

            rvRequestFileList.adapter = UploadAdapter(applicationContext, uriList)
            // recyclerView 아이템에 각각 스페이싱
            rvRequestFileList.addItemDecoration(HorizontalSpacingItemDecoration(64))
        }

        val files = arrayListOf<File>()

        uriList.map {

            files.add(File(it.path))
            Log.d("files", it.path.toString())
        }

        files.map {
            val requestBody = RequestBody.create(okhttp3.MediaType.parse("multipart/form-data"), it)
            val part = MultipartBody.Part.createFormData("images", it.name, requestBody)
            body.add(part)
        }
    }

    // 2019.09.27 지도와 주소 위치 정보 받아오는 로직 by Hudson
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK)
            return

        with(binding) {
            if (requestCode == RC_MAP_RESULT || requestCode == RC_TEXT_ADDRESS_RESULT) {
                //mainAddress = data!!.getStringExtra("mainAddress")
                //detailAddress = data!!.getStringExtra("detailAddress")
                tvAddress.text = ""
                //    tvAddress.text = "$mainAddress $detailAddress"
            }
        }
    }

}
