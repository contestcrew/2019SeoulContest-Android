package com.seoulcontest.firstcitizen.ui.dialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.DialogHelpBinding
import com.seoulcontest.firstcitizen.network.RetrofitHelper
import com.seoulcontest.firstcitizen.ui.help.HelpUploadActivity
import com.seoulcontest.firstcitizen.ui.main.MainActivity

class HelpDialog : DialogFragment(), View.OnClickListener {

    lateinit var binding: DialogHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_help, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()

    }

    override fun onClick(view: View?) {

        when (view!!.id) {
            // 확인 버튼 클릭 시
            binding.btnConfirmHelp.id -> {

                // 2019.09.29 똥휴지 Request 생성 by Hudosn
                // RetrofitHelper.getInstance().apiService.createRequest()

                dismiss()
                // HelpUploacActivity로 이동
                startActivity(Intent(context, MainActivity::class.java))
            }

            binding.btnCancelHelp.id -> {
                dismiss()
            }
        }
    }

    private fun initView() {

        // 2019.09.20 다이얼로그 바깥쪽 클릭 무시 by Hudson
        dialog!!.setCanceledOnTouchOutside(false)

        // 버튼 리스너 초기화
        binding.btnConfirmHelp.setOnClickListener(this)
        binding.btnCancelHelp.setOnClickListener(this)

    }
}