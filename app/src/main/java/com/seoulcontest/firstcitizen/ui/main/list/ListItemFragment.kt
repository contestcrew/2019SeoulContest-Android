package com.seoulcontest.firstcitizen.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.FragmentListItemBinding

class ListItemFragment : Fragment() {

    private var keyCategory = "KEY_CATEGORY"
    private lateinit var binding: FragmentListItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_item, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val currCategory = arguments?.getInt(keyCategory, 0)

        initRecyclerView(currCategory)
    }

    private fun initRecyclerView(category: Int?) {
        binding.rvListItem.adapter = ListItemRvAdapter().apply {
            loadData(category)
        }
    }

    companion object {
        fun newInstance(categoryId: Int) = ListItemFragment().apply {
            arguments = Bundle().apply { putInt(keyCategory, categoryId) }
        }
    }
}