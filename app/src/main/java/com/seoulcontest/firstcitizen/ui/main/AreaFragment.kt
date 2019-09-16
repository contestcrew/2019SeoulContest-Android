package com.seoulcontest.firstcitizen.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentMapBinding

class AreaFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val mapBinding : FragmentMapBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_map,container,false)

        return mapBinding.root

    }

}
