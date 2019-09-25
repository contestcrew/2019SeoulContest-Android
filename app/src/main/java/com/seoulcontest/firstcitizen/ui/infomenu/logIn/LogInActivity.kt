package com.seoulcontest.firstcitizen.ui.infomenu.logIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {


    lateinit var binding: ActivityLogInBinding

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

        }

        binding.btnSignUp.setOnClickListener {

            startActivity(Intent(this, SignUpActivity::class.java))
        }

    }

}
