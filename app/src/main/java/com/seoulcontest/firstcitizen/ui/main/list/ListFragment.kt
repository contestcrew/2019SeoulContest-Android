package com.seoulcontest.firstcitizen.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentListBinding
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel

class ListFragment : Fragment() {

    private val viewModel = MainViewModel.getInstance()

    private lateinit var listItemAdapter: ListItemAdapter
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        initView()

    }

    private fun initViewModel() {
        binding.viewModel = viewModel
    }

    private fun initView() {
        //init viewPager
        listItemAdapter =
            ListItemAdapter(requireActivity().supportFragmentManager)

        binding.viewPagerLists.adapter = listItemAdapter
        binding.tabLayoutCatergory.setupWithViewPager(binding.viewPagerLists)
    }

}