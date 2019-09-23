package com.seoulcontest.firstcitizen.ui.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityHelpUploadBinding
import com.seoulcontest.firstcitizen.ui.dialog.PostFilesDialog
import com.seoulcontest.firstcitizen.util.HorizontalSpacingItemDecoration

class HelpUploadActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityHelpUploadBinding
    val RC_GET_IMAGES = 0
    val RC_GET_VIDEOS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_help_upload)

        initView()
    }

    private fun initView() {

        binding.btnUploadHelp.setOnClickListener(this)
        binding.btnSelectFile.setOnClickListener(this)
        binding.rvFileList.addItemDecoration(HorizontalSpacingItemDecoration(16))


    }

    override fun onClick(view: View?) {

        when(view!!.id){

            binding.btnSelectFile.id -> {

                val dialog = PostFilesDialog(applicationContext)
                dialog.show(supportFragmentManager.beginTransaction(),"")

            }

            binding.btnUploadHelp.id -> {


            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }

}
