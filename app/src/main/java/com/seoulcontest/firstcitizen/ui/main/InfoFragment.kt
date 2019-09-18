package com.seoulcontest.firstcitizen.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private lateinit var mContext : Context
    private lateinit var binding : FragmentInfoBinding
    private var categoryArray : ArrayList<Category> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = context!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // View Binding
        val rv_request = binding.rvRequest

        // 가 데이터 적용
        categoryArray.add(Category("crash"))
        categoryArray.add(Category("crash"))
        categoryArray.add(Category("missing"))
        categoryArray.add(Category("restroom"))
        categoryArray.add(Category("loss"))


        // 2019.09.18 rv_request 어댑터 적용 by Hudson
        rv_request.adapter = InfoRequestAdapter(mContext,categoryArray)

        // horizontal layout 적용
        val layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)
        rv_request.layoutManager = layoutManager

        rv_request.setHasFixedSize(true)


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_info,container,false)
        return binding.root
    }

}