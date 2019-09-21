package com.seoulcontest.firstcitizen.ui.help

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityHelpUploadBinding

class HelpUploadActivity : AppCompatActivity() {

    lateinit var binding : ActivityHelpUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_help_upload)

    }
}
