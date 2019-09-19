package com.seoulcontest.firstcitizen.ui.main

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
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentInfoBinding
import com.seoulcontest.firstcitizen.ui.service.NoticeActivity
import com.seoulcontest.firstcitizen.ui.service.ServiceTermsActivity

class InfoFragment : Fragment(), View.OnClickListener {

    private lateinit var mContext: Context
    // 뷰 바인딩
    private lateinit var binding: FragmentInfoBinding

    private var infoMenuArray = arrayOf("의뢰", "제보", "공지사항", "이용약관", "내정보")
    private var isLog: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = context!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 2019.09.19 View Binding by Hudson
        initView()

        val totalCount = Request(10).totalCount

        // 2019.09.18 rv_request 어댑터 적용 by Hudson
        binding.rvRequest.adapter = InfoMenuAdapter(mContext, infoMenuArray, totalCount)

        // horizontal layout 적용
        val layoutManager = LinearLayoutManager(mContext)

        binding.rvRequest.layoutManager = layoutManager

        // 리사이클러뷰 크기 고정
        binding.rvRequest.setHasFixedSize(true)

        // 리사이클러뷰 스크롤 막기
        binding.rvRequest.setOnTouchListener({
            view: View?, motionEvent: MotionEvent? ->
            true
        })

    }

    private fun initView() {

        binding.btnLog.setOnClickListener(this)

    }

    // 2019.09.19 로그인/아웃 여부에 따라 뷰 visibility 적용 by Hudson
    private fun showHide(view: View, isLog: Boolean) {
        view.visibility = if (!isLog) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        return binding.root
    }

    override fun onClick(view: View?) {

        var intentLocation: Intent? = null
        // 클릭한 뷰의 아이디에 따라 각 기능 수행
        when (view!!.id) {

            binding.btnLog.id -> {
                if (isLog) {
                    // todo : fragment 교체해 줄 것
                }
            }
        }
        // 다른 액티비티로 이동
        if (intentLocation != null) {
            startActivity(intentLocation)
        }
    }
}