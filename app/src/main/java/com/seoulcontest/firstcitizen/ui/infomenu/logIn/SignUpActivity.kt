package com.seoulcontest.firstcitizen.ui.infomenu.logIn

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.iid.FirebaseInstanceId
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.Account
import com.seoulcontest.firstcitizen.data.vo.User
import com.seoulcontest.firstcitizen.databinding.ActivitySignUpBinding
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import retrofit2.Call as Call

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        initEvent()
    }

    private fun initEvent() {
        with(binding) {

            btnSignUp.setOnClickListener {

                // 이메일이 입력되어 있고 비밀번호가 일치하지 않으면
                if (etPassword.text.toString() != edtCheckPass.text.toString()) {
                    etPassword.text.clear()
                    edtCheckPass.text.clear()
                    Toast.makeText(this@SignUpActivity, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()

                } else if (etPassword.text.toString().trim() == "") {
                    Toast.makeText(this@SignUpActivity, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()

                } // 이메일이 입력되어 있지 않는 경우
                else if (etEmail.text.toString().trim() == "") {
                    Toast.makeText(this@SignUpActivity, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    createAccount()
                }
            }
        }
    }

    private fun createAccount() {

        val newUser = Account(
            "${binding.etEmail.text}",
            "${binding.etPassword.text}",
            "${FirebaseInstanceId.getInstance().token}"
        )

        RetrofitHelper
            .getInstance()
            .apiService
            .createAccount(newUser)
            .enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity, "회원가입에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {

                    if (response.isSuccessful) {
                        // 성공하면 인텐트 결과값 전달
                        setResult(Activity.RESULT_OK, Intent())
                        finish()
                    }
                }
            })
    }
}
