package com.seoulcontest.firstcitizen.ui.help

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityHelpUploadBinding
import com.seoulcontest.firstcitizen.ui.dialog.PostFilesDialog
import com.seoulcontest.firstcitizen.ui.infomenu.NoticeActivity
import com.seoulcontest.firstcitizen.ui.upload.UploadAdapter
import com.seoulcontest.firstcitizen.util.HorizontalSpacingItemDecoration
import gun0912.tedimagepicker.builder.TedImagePicker
import gun0912.tedimagepicker.builder.type.MediaType

class HelpUploadActivity : AppCompatActivity() {

    lateinit var binding: ActivityHelpUploadBinding
    private lateinit var helpUploadAdapter: UploadAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help_upload)

        initView()
        initEvent()
    }

    private fun initView() {

        binding.rvFileList.addItemDecoration(HorizontalSpacingItemDecoration(16))

        // 2019.09.23 경찰 정보 제공 의무 텍스트에 밑줄 적용 by Hudson
        val content = SpannableString(binding.btnInfoAct.text)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.btnInfoAct.text = content

    }

    private fun initEvent() {

        with(binding) {
            btnUploadHelp.setOnClickListener {

                // text와 message 띄어쓰기 처리
                val title = binding.edtHelpUploadTitle.text.trim()
                val message = binding.edtHelpUploadMessage.text.trim()

                // 타이틀과 메세지가 비어있지 않다면
                if (!(title == "" && message == "")) {
                    // todo : 데이터 전달

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
        }
    }

    private fun goToAlbum() {
        TedImagePicker.with(this).mediaType(MediaType.IMAGE).startMultiImage { uriList ->
            setFiles(uriList)
        }
    }

    private fun setFiles(uriList: List<Uri>) {
        // 리사이클러 뷰
        with(binding) {
            rvFileList.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)

            helpUploadAdapter = UploadAdapter(applicationContext)
            rvFileList.adapter = helpUploadAdapter
            // recyclerView 아이템에 각각 스페이싱
            rvFileList.addItemDecoration(HorizontalSpacingItemDecoration(64))
        }

        helpUploadAdapter.setData(uriList)
    }
}
