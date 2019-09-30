package com.seoulcontest.firstcitizen.ui.help

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.Report
import com.seoulcontest.firstcitizen.databinding.ActivityHelpUploadBinding
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import com.seoulcontest.firstcitizen.ui.infomenu.NoticeActivity
import com.seoulcontest.firstcitizen.ui.upload.ImageUploadAdapter
import com.seoulcontest.firstcitizen.util.HorizontalSpacingItemDecoration
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType
import okhttp3.Callback
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.ArrayList

class HelpUploadActivity : AppCompatActivity() {

    lateinit var binding: ActivityHelpUploadBinding
    private var isInformed = true // 경찰 정보 제공 동의 유무
    private var title: String? = null   // Report 의 제목
    private var message: String? = null // Report 의 내용
    private var requestId: Int? = -1 // 선택한 의뢰의 아이디

    private val parts = ArrayList<MultipartBody.Part>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help_upload)

        initView()
        initEvent()
    }

    private fun initView() {

        if (intent != null) {

            requestId = intent.getIntExtra("requestId", -1)
        }

        with(binding) {

            rvFileList.addItemDecoration(HorizontalSpacingItemDecoration(16))

            // 2019.09.23 경찰 정보 제공 의무 텍스트에 밑줄 적용 by Hudson
            val content = SpannableString(btnInfoAct.text)
            content.setSpan(UnderlineSpan(), 0, content.length, 0)
            btnInfoAct.text = content
        }
    }

    private fun initEvent() {

        with(binding) {
            btnUploadHelp.setOnClickListener {

                // text와 message 띄어쓰기 처리
                title = edtHelpUploadTitle.text.toString().trim()
                message = edtHelpUploadMessage.text.toString().trim()

                // 제목 또는 내용이 비어있지 않다면
                if (!(title == "" || message == "")) {
                    createNewReport()

                } else {
                    Toast.makeText(this@HelpUploadActivity, "타이틀과 메세지를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }

            // 경찰 정보 제공 동의 문구 클릭 시 공지사항으로 이동
            btnInfoAct.setOnClickListener {
                startActivity(Intent(this@HelpUploadActivity, NoticeActivity::class.java))
            }

            // 2019.09.27 파일 추가 버튼 클릭 시 앨범으로 이동하여 이미지 가져오기 by Hudson
            btnSelectFile.setOnClickListener {
                goToAlbum()
            }

            // 2019.09.30 경찰 정보 제공 유무 Boolean 값 받기 by Hudson
            switchPublic.setOnClickListener {
                isInformed = switchPublic.isChecked
                Log.d("isInformed", isInformed.toString())
            }
        }
    }

    private fun goToAlbum() {
        TedImagePicker.with(this).mediaType(MediaType.IMAGE).startMultiImage { uriList ->
            setFiles(uriList)
            setImages(uriList)
        }
    }

    private fun setImages(uriList: List<Uri>) {
        parts.clear()

        val files = arrayListOf<File>()
        uriList.map {

            files.add(File(it?.path))
        }

        files.map {
            val requestBody = RequestBody.create(okhttp3.MediaType.parse("images/*"), it)
            val part = MultipartBody.Part.createFormData("images", it.name, requestBody)

            parts.add(part)
        }
    }

    private fun setFiles(uriList: List<Uri>) {
        // 리사이클러 뷰
        with(binding) {
            rvFileList.adapter = ImageUploadAdapter(this@HelpUploadActivity).apply {
                setUriImages(uriList)
            }
            // recyclerView 아이템에 각각 스페이싱
            rvFileList.addItemDecoration(HorizontalSpacingItemDecoration(64))
        }
    }

    private fun createNewReport() {
        val userInfo = MainViewModel.getInstance().user.get()
        val author = Report.Author(userInfo!!.id, userInfo!!.email, userInfo.mannerScore)

        val helpTime = SimpleDateFormat("y-M-d k:m:s").format(System.currentTimeMillis())
        val partMap = HashMap<String, RequestBody>()
        partMap["request"] = RequestBody.create(okhttp3.MediaType.parse("text/plain"), requestId.toString())
        partMap["is_agreed_inform"] = RequestBody.create(okhttp3.MediaType.parse("text/plain"), isInformed.toString())
        partMap["author"] = RequestBody.create(okhttp3.MediaType.parse("text/plain"), author.toString())
        partMap["title"] = RequestBody.create(okhttp3.MediaType.parse("text/plain"), title)
        partMap["content"] = RequestBody.create(okhttp3.MediaType.parse("text/plain"), message)
        partMap["helped_at"] = RequestBody.create(okhttp3.MediaType.parse("text/plain"), helpTime)

        RetrofitHelper.getInstance().apiService.createReport(
            "Token ${MainViewModel.getInstance().userToken}",
            partMap,
            parts
        ).enqueue(object : retrofit2.Callback<Report> {
            override fun onFailure(call: Call<Report>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Report>, response: Response<Report>) {
                if (response.isSuccessful) {
                    Log.d("response", "response ${response.body()}")
                }
            }
        })
    }
}
