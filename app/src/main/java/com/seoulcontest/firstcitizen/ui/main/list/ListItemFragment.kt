package com.seoulcontest.firstcitizen.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentCategoryBinding

class ListItemFragment : Fragment() {

    private var key = "KEY_CATEGORY"
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val currCategory = arguments?.getString(key)

        binding.textView.text = "카테고리별 요쳥 보여줄 예정\n 현재 카테고리 : $currCategory"
    }

    companion object {
        fun newInstance(category: String) = ListItemFragment().apply {
            arguments = Bundle().apply { putString(key, category) }
        }
    }
}