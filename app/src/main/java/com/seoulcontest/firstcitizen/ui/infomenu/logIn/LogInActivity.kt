package com.seoulcontest.firstcitizen.ui.infomenu.logIn

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {


    lateinit var binding: ActivityLogInBinding
    private val RC_SIGN_UP_OK = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in)

        initView()
        initEvent()

    }

    private fun initView() {


    }


    private fun initEvent() {

        binding.btnLogIn.setOnClickListener {

            // todo : 서버에 유효한 아이디와 비밀번호인지 인증한 후 토큰 값 얻어오기

        }

        binding.btnToSignUp.setOnClickListener {

            startActivityForResult(Intent(this, SignUpActivity::class.java), RC_SIGN_UP_OK)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK)
            return

        if (requestCode== RC_SIGN_UP_OK) {

            Toast.makeText(this,"회원가입에 성공했습니다.",Toast.LENGTH_SHORT).show()

        }


    }

}
