package com.seoulcontest.firstcitizen.ui.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityHelpUploadBinding
import com.seoulcontest.firstcitizen.ui.dialog.PostFilesDialog
import com.seoulcontest.firstcitizen.ui.infomenu.NoticeActivity
import com.seoulcontest.firstcitizen.util.HorizontalSpacingItemDecoration

class HelpUploadActivity : AppCompatActivity() {

    lateinit var binding: ActivityHelpUploadBinding
    val RC_GET_IMAGES = 0
    val RC_GET_VIDEOS = 1

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

        binding.btnSelectFile.setOnClickListener {

            val dialog = PostFilesDialog(applicationContext)
            dialog.show(supportFragmentManager.beginTransaction(), "")

        }

        binding.btnUploadHelp.setOnClickListener {

            // text와 message 띄어쓰기 처리
            val title = binding.edtHelpUploadTitle.text.trim()
            val message = binding.edtHelpUploadMessage.text.trim()

            // 타이틀과 메세지가 비어있지 않다면
            if (!(title == "" && message == "")) {
                // todo : 데이터 전달

            } else {
                Toast.makeText(this, "타이틀과 메세지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 경찰 정보 제공 동의 문구 클릭 시 공지사항으로 이동
        binding.btnInfoAct.setOnClickListener {
            startActivity(Intent(this, NoticeActivity::class.java))
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }
}
