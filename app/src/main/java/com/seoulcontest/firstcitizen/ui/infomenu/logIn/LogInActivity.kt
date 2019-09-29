package com.seoulcontest.firstcitizen.ui.infomenu.logIn

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.GetTokenRequest
import com.seoulcontest.firstcitizen.data.vo.GetTokenResponse
import com.seoulcontest.firstcitizen.data.vo.User
import com.seoulcontest.firstcitizen.databinding.ActivityLogInBinding
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private val viewModel = MainViewModel.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in)

        initEvent()
    }

    private fun initEvent() {
        binding.btnLogIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            RetrofitHelper
                .getInstance()
                .apiService
                .getToken(GetTokenRequest(email, password))
                .enqueue(object : Callback<GetTokenResponse> {
                    override fun onFailure(call: Call<GetTokenResponse>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<GetTokenResponse>, response: Response<GetTokenResponse>) {
                        if (response.isSuccessful) {
                            val responseToken = response.body()

                            if (responseToken != null) {
                                viewModel.userToken = responseToken.token
                                viewModel.isLogIn.set(true)

                                RetrofitHelper
                                    .getInstance()
                                    .apiService
                                    .getUser(responseToken.id)
                                    .enqueue(object : Callback<User> {
                                        override fun onFailure(call: Call<User>, t: Throwable) {
                                            t.printStackTrace()
                                        }

                                        override fun onResponse(call: Call<User>, response: Response<User>) {
                                            val currUser = response.body()

                                            if (currUser != null) {
                                                viewModel.user.set(currUser)
                                                Toast.makeText(applicationContext, "로그인 되었습니다", Toast.LENGTH_SHORT)
                                                    .show()
                                                finish()
                                            } else {
                                                Log.d("test", currUser.toString())
                                            }
                                        }
                                    })
                            }
                        } else {
                            Toast.makeText(applicationContext, "올바른 데이터 형식이 아닙니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                })

        }

        binding.btnToSignUp.setOnClickListener {
            startActivityForResult(Intent(this, SignUpActivity::class.java), RC_SIGN_UP_OK)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode == RC_SIGN_UP_OK) {
            Toast.makeText(this, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val RC_SIGN_UP_OK = 1000
    }
}
