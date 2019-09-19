package com.seoulcontest.firstcitizen.ui.service

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityServiceTermsBinding

class ServiceTermsActivity : AppCompatActivity() {

    lateinit var binding : ActivityServiceTermsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service_terms)

    }
}
