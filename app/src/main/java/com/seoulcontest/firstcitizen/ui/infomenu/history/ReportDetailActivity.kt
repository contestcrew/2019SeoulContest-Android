package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.seoulcontest.firstcitizen.databinding.ActivityReportDetailBinding
import com.seoulcontest.firstcitizen.ui.upload.ImageUploadAdapter
import com.seoulcontest.firstcitizen.viewmodel.ReportDetailViewModel

class ReportDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportDetailBinding
    private lateinit var adapter: ImageUploadAdapter
    private val viewModel by lazy { ReportDetailViewModel() }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityReportDetailBinding.inflate(LayoutInflater.from(this))
        binding.viewModel = viewModel

        initRecyclerView()

        val reportId = intent.getIntExtra("reportId", 0)
        viewModel.loadData(reportId)
    }

    private fun initRecyclerView() {
        adapter = ImageUploadAdapter(this)
        binding.rvReports.adapter = adapter
    }
}
