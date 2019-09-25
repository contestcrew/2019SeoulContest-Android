package com.seoulcontest.firstcitizen.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityDetailBinding
import com.seoulcontest.firstcitizen.ui.dialog.HelpDialog

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    // todo : enum class 만드는 거 어떻게 생각하시는지???
    // todo : Request 객체 아래 properties로 구분하자는 의견(서버)

    // 뷰 바인딩
    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    // 2019.09.20 뷰 초기화 by Hudson
    fun initView() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.btHelp.setOnClickListener(this)


    }

    // 2019.09.20 도와주기 버튼 클릭 시 이동하는 로직 작성 by hudson
    override fun onClick(view: View?) {

        when (view!!.id) {

            // 도와주기 버튼 클릭 시 다이얼로그 띄어주기
            binding.btHelp.id -> {
                val helpDialog = HelpDialog()
                helpDialog.show(supportFragmentManager.beginTransaction(), "")
            }
        }
    }
}
