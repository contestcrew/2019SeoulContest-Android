package com.seoulcontest.firstcitizen.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.DialogPostFilesBinding

class PostFilesDialog(private val mContext: Context) : DialogFragment(), View.OnClickListener {

    lateinit var binding: DialogPostFilesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_post_files, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    private fun initView() {

        binding.btnAddImages.setOnClickListener(this)
        binding.btnAddVideos.setOnClickListener(this)
        binding.btnCancelAddition.setOnClickListener(this)

        dialog.setCanceledOnTouchOutside(false)

    }

    override fun onClick(view: View?) {

        when (view!!.id) {

            binding.btnAddImages.id -> {

                dismiss()

            }

            binding.btnAddVideos.id -> {

                dismiss()

            }

            binding.btnCancelAddition.id -> {

                dismiss()

            }
        }
    }


    private fun setFileSelectListener() {

    }
}