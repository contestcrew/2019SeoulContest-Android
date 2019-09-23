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
import com.seoulcontest.firstcitizen.ui.help.HelpUploadActivity

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

        when(view!!.id){
            // 확인 버튼 클릭 시
            binding.btnConfirmHelp.id -> {

                dismiss()
                // HelpUploacActivity로 이동
                val helpIntent = Intent(context, HelpUploadActivity::class.java)
                startActivity(helpIntent)
            }

            binding.btnCancelHelp.id -> {
                dismiss()
            }
        }
    }

    private fun initView() {

        // 2019.09.20 다이얼로그 바깥쪽 클릭 무시 by Hudson
        dialog.setCanceledOnTouchOutside(false)

        // 버튼 리스너 초기화
        binding.btnConfirmHelp.setOnClickListener(this)
        binding.btnCancelHelp.setOnClickListener(this)

    }
}