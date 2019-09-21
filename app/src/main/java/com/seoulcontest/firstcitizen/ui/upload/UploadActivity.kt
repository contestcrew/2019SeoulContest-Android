package com.seoulcontest.firstcitizen.ui.upload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var upload : ActivityUploadBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        upload = DataBindingUtil.setContentView(this,R.layout.activity_upload)

        // 2019.09.16 UploadActivity 초기화 by Hudson
        initView()

    }


   private fun initView(){

       val category = intent.getStringExtra("binding")
        upload.tvCategory.text = category
       upload.btnRequest.setOnClickListener(this)
       upload.switchPublic.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

    }

}
