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
    private lateinit var binding: FragmentListItemBinding
    private lateinit var adapter: ListItemRvAdapter
    var category = 0

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

        category = arguments?.getInt(KEY_CATEGORY, 0) ?: 0
        initRecyclerView()
        loadFragmentData()
    }

    private fun initRecyclerView() {
        adapter = ListItemRvAdapter()
        binding.rvListItem.adapter = adapter
    }

    fun loadFragmentData() {
        adapter.loadData(category)
    }

    fun searchRequest(query: String?) {
        adapter.searchData(query)
    }

    companion object {
        const val KEY_CATEGORY = "KEY_CATEGORY"

        fun newInstance(categoryId: Int) = ListItemFragment().apply {
            arguments = Bundle().apply { putInt(KEY_CATEGORY, categoryId) }
        }
    }
}