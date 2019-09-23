package com.seoulcontest.firstcitizen.ui.upload

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityUploadBinding
import com.seoulcontest.firstcitizen.ui.infomenu.NoticeActivity
import com.seoulcontest.firstcitizen.util.HorizontalSpacingItemDecoration

class UploadActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upload)

        // 2019.09.16 UploadActivity 초기화 by Hudson
        initView()
    }


    private fun initView() {
        // 넘어온 카테고리에 해당하는 텍스트 적용
        val category = intent.getStringExtra("binding")
        binding.tvCategory.text = category

        // 2019.09.23 경찰 정보 제공 의무 텍스트에 밑줄 적용 by Hudson
        val content = SpannableString(binding.btnInfoAct.text)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.btnInfoAct.text = content

        // 리스너
        binding.btnRequest.setOnClickListener(this)
        binding.switchPublic.setOnClickListener(this)

        // 리사이클러 뷰
        val layoutManager = LinearLayoutManager(
            applicationContext, LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvRequestFileList.layoutManager = layoutManager

        val imageArr = arrayOf(R.drawable.img_test01, R.drawable.img_test02, R.drawable.img_test03)
        binding.rvRequestFileList.adapter = UploadAdapter(applicationContext, imageArr)
        // recyclerView 아이템에 각각 스페이싱
        binding.rvRequestFileList.addItemDecoration(HorizontalSpacingItemDecoration(64))

    }

    override fun onClick(view: View?) {

        when (view!!.id) {

            binding.btnInfoAct.id -> {

                startActivity(Intent(this, NoticeActivity::class.java))

            }

        }
    }
}
