package com.seoulcontest.firstcitizen.ui.main.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.User
import com.seoulcontest.firstcitizen.databinding.FragmentInfoBinding
import com.seoulcontest.firstcitizen.ui.infomenu.logIn.LogInActivity

class InfoFragment : Fragment() {

    private lateinit var mContext: Context
    // 뷰 바인딩
    private lateinit var binding: FragmentInfoBinding

    private var logInMenuArray = arrayOf("의뢰", "제보", "공지사항", "이용약관", "내정보")
    private var logOutMenuArray = arrayOf("공지사항", "이용약관")
    // 로그인/아웃 상태
    private var isLogIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = context!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 2019.09.19 View Binding by Hudson
        initView()
        showHideByLogIn()
        // 2019.09.25 로그인/아웃 클릭 이벤트 처리 by Hudson
        initEvent()
    }

    private fun initView() {

        // horizontal layout 적용
        val layoutManager = LinearLayoutManager(mContext)

        binding.rvRequest.layoutManager = layoutManager

        // 리사이클러뷰 크기 고정
        binding.rvRequest.setHasFixedSize(true)

        // 리사이클러뷰 스크롤 막기
        binding.rvRequest.setOnTouchListener({ view: View?, motionEvent: MotionEvent? ->
            true
        })
    }

    // 2019.09.25 로그인/아웃 여부에 따라 뷰 상태 초기화 by Hudson
    private fun showHideByLogIn() {

        // 2019.09.25 UserData 초기화 by Hudson
        val userData = User(0, "박현우", "", "hudson", "", "", "psh8960@naver.com", 0, 1, 0, 100, 30, "")
        val totalCount = 2

        // 2019.09.18 rv_request 어댑터 적용 by Hudson
        // 2019.09.25 로그인/아웃 상태에 따라 다른 뷰 적용 by Hudson
        with(binding) {
            if (isLogIn) {

                rvRequest.adapter = InfoMenuAdapter(mContext, logInMenuArray, totalCount, isLogIn)
                ivProfile.visibility = View.VISIBLE
                // todo : 프로필 어떻게 처리할 것인지????
                tvNick.text = "별명 : ${userData.nickname}"
                tvReliability.text = "신뢰도 : ${userData.mannerScore}"
                tvPoint.text = "Point : ${userData.citizenScore}"
                btnLog.text = getString(R.string.logOut_text)

            } else {

                rvRequest.adapter = InfoMenuAdapter(mContext, logOutMenuArray, -1, isLogIn)
                ivProfile.visibility = View.INVISIBLE
                tvNick.text = getString(R.string.nick_logout)
                tvReliability.text = getString(R.string.reliability_logout)
                tvPoint.text = getString(R.string.point_logout)
                btnLog.text = getString(R.string.logIn_text)

            }
        }
    }

    private fun initEvent() {

       lateinit var logInIntent : Intent

        binding.btnLog.setOnClickListener {

            // 로그인 상태일 때
            if (isLogIn) {
                // 로그아웃 처리 후 AreaFragment 로 이동

            } else {
                // 로그인을 위해 로그인 페이지로 이동
                logInIntent = Intent(mContext,LogInActivity::class.java)
            }

            logInIntent.putExtra("isLogIn",isLogIn)
            startActivity(logInIntent)
        }
    }
}
