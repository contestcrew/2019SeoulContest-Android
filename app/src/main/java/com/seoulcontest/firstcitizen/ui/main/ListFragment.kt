package com.seoulcontest.firstcitizen.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var vpAdapter : ListAdapter

    private lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    private fun initView(){
        //init viewPager
        vpAdapter = ListAdapter(activity!!.supportFragmentManager)

        binding.viewPagerLists.adapter = vpAdapter
        binding.tabLayoutCatergory.setupWithViewPager(binding.viewPagerLists)
    }

}