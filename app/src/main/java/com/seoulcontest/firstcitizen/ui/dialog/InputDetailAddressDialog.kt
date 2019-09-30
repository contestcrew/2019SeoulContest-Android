package com.seoulcontest.firstcitizen.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.DialogInputDetailAddressBinding
import com.seoulcontest.firstcitizen.util.DetailAddressInterface

class InputDetailAddressDialog : DialogFragment() {
    private lateinit var binding: DialogInputDetailAddressBinding
    private lateinit var mListener: DetailAddressInterface

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mainAddress = arguments?.getString("address")

        // 2019.09.27 다이얼로그 바깥쪽 클릭 무시 by Hudson
        dialog!!.setCanceledOnTouchOutside(false)

        with(binding) {
            // 2019.09.27 상세 주소 입력 후 입력 버튼 클릭 시 UploadActivity로 이동 by Hudson
            tvSelectedAddress.text = mainAddress

            btnConfirmAddress.setOnClickListener {
                val detailAddress = edtDetailAddress.text.toString().trim()

                if (!detailAddress.isNullOrEmpty()) {
                    mListener.onConfirmSelected(detailAddress)
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "상세 주소를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
            // 2019.09.27 취소 버튼 클릭 시 InputDetailAddressDialog 종료 by Hudson
            btnCancelAddress.setOnClickListener {
                mListener.onCancelSelected()
                dismiss()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 2019.09.27 카테고리 다이얼로그 관련 로직 추가 by Hudson
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_input_detail_address, container, false)
        return binding.root
    }

    fun setOnDialogButtonClickListener(listener: DetailAddressInterface) {
        mListener = listener
    }
}