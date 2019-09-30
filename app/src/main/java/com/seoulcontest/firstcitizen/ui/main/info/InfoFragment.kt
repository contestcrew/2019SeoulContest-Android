package com.seoulcontest.firstcitizen.ui.main.info

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentInfoBinding
import com.seoulcontest.firstcitizen.ui.infomenu.MyInfoActivity
import com.seoulcontest.firstcitizen.ui.infomenu.NoticeActivity
import com.seoulcontest.firstcitizen.ui.infomenu.ServiceTermsActivity
import com.seoulcontest.firstcitizen.ui.infomenu.history.HistoryHelpActivity
import com.seoulcontest.firstcitizen.ui.infomenu.history.RequestDetailActivity
import com.seoulcontest.firstcitizen.ui.infomenu.history.RequestHistoryActivity
import com.seoulcontest.firstcitizen.ui.infomenu.logIn.LogInActivity
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel

class InfoFragment : Fragment() {
    private val viewModel = MainViewModel.getInstance()
    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        initEvent()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
    }

    private fun initEvent() {
        binding.btnLogInAndOut.setOnClickListener {
            val isLogIn = viewModel.isLogIn.get() ?: false

            if (isLogIn) {
                viewModel.user.set(null)
                viewModel.isLogIn.set(false)
                Toast.makeText(requireContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(requireContext(), LogInActivity::class.java))
            }
        }

        binding.ivRequest.setOnClickListener {
            startActivity(Intent(requireContext(), RequestHistoryActivity::class.java))
        }

        binding.ivHelp.setOnClickListener {
            startActivity(Intent(requireContext(), HistoryHelpActivity::class.java).apply {
                // 로그인한 이용자의 토큰값 전달
                Log.d("token", "token : ${viewModel.userToken}")
                putExtra("userToken", viewModel.userToken)
            })
        }

        binding.ivNotice.setOnClickListener {
            startActivity(Intent(requireContext(), NoticeActivity::class.java))
        }

        binding.ivPolicy.setOnClickListener {
            startActivity(Intent(requireContext(), ServiceTermsActivity::class.java))
        }

        binding.ivMyInfo.setOnClickListener {
            startActivity(Intent(requireContext(), MyInfoActivity::class.java))
        }




        binding.tvRequest.setOnClickListener {
            startActivity(Intent(requireContext(), RequestHistoryActivity::class.java))
        }

        binding.tvHelp.setOnClickListener {
            startActivity(Intent(requireContext(), HistoryHelpActivity::class.java).apply {
                // 로그인한 이용자의 토큰값 전달
                Log.d("token", "token : ${viewModel.userToken}")
                putExtra("userToken", viewModel.userToken)
            })
        }

        binding.tvNotice.setOnClickListener {
            startActivity(Intent(requireContext(), NoticeActivity::class.java))
        }

        binding.tvPolicy.setOnClickListener {
            startActivity(Intent(requireContext(), ServiceTermsActivity::class.java))
        }

        binding.tvMyinfo.setOnClickListener {
            startActivity(Intent(requireContext(), MyInfoActivity::class.java))
        }
    }
}
