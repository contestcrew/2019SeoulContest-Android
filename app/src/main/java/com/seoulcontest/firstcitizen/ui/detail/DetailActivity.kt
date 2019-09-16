package com.seoulcontest.firstcitizen.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityDetailBinding
import com.seoulcontest.firstcitizen.ui.main.CategoryDialog

class DetailActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var detail : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detail = DataBindingUtil.setContentView(this,R.layout.activity_detail)

        detail.btnRequestdialog.setOnClickListener(this)
    }


    override fun onClick(view: View?) {

        // 2019.09.15 버튼 클릭 시 다이얼로그 띄우는 로직 추가 by Hudson
        if (view!!.id == detail.btnRequestdialog.id){

            val dialog = CategoryDialog(applicationContext)

            dialog.show(supportFragmentManager.beginTransaction(),"")

        }

    }
}
