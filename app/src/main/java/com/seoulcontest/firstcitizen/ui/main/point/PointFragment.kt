package com.seoulcontest.firstcitizen.ui.main.point

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentPointBinding
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel

class PointFragment : Fragment(), View.OnClickListener {

    lateinit var binding: FragmentPointBinding
    private val mainViewModel by lazy { MainViewModel.getInstance() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_point, container, false)
        binding.mainViewModel = mainViewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        // 2019.09.28 이미지 뷰 초기화 로직(리펙토링 심히 필요할 듯) by Hudson
        with(binding) {
            // 라운드 처리하는 drawable 반환
            requireContext().getDrawable(R.drawable.iv_rounding).let {
                with(ivPlace01) {
                    background = it
                    clipToOutline = true
                    Glide.with(this@PointFragment).load(R.drawable.ic_place01).into(this)
                }
                with(ivPlace02) {
                    background = it
                    clipToOutline = true
                    Glide.with(this@PointFragment).load(R.drawable.ic_place02).into(this)
                }
                with(ivPlace03) {
                    background = it
                    clipToOutline = true
                    Glide.with(this@PointFragment).load(R.drawable.ic_place03).into(this)
                }
                with(ivPlace04) {
                    background = it
                    clipToOutline = true
                    Glide.with(this@PointFragment).load(R.drawable.ic_place04).into(this)
                }
                with(ivPlace05) {
                    background = it
                    clipToOutline = true
                    Glide.with(this@PointFragment).load(R.drawable.ic_place05).into(this)
                }

                // 리스너 초기화
                initClickListener()
            }
        }
    }

    private fun FragmentPointBinding.initClickListener() {
        btnPublishCoupon01.setOnClickListener(this@PointFragment)
        btnPublishCoupon02.setOnClickListener(this@PointFragment)
        btnPublishCoupon03.setOnClickListener(this@PointFragment)
        btnPublishCoupon04.setOnClickListener(this@PointFragment)
        btnPublishCoupon05.setOnClickListener(this@PointFragment)
        layoutGettingPoint.setOnClickListener(this@PointFragment)
    }

    override fun onClick(view: View?) {
        showNotYearServiceDialog()
    }

    private fun showNotYearServiceDialog() {
        AlertDialog.Builder(requireContext()).let {
            it.setTitle("서비스 예정")
            it.setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss()
            }.show()
        }
    }
}