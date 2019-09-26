package com.seoulcontest.firstcitizen.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.data.vo.Category
import com.seoulcontest.firstcitizen.databinding.FragmentListBinding
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
import android.content.Context
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private val viewModel = MainViewModel.getInstance()

    private lateinit var listItemAdapter: ListItemAdapter
    private lateinit var binding: FragmentListBinding

    private lateinit var imm: InputMethodManager

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

        imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        initViewModel()
        initView()
        initEvent()
        initCallback()
    }

    private fun initViewModel() {
        binding.viewModel = viewModel
    }

    private fun initView() {
        //init viewPager
        listItemAdapter =
            ListItemAdapter(requireActivity().supportFragmentManager)

        with(binding) {
            viewPagerLists.adapter = listItemAdapter
            tabLayoutCatergory.setupWithViewPager(binding.viewPagerLists)
        }
    }

    private fun initEvent() {
        binding.btSearch.setOnClickListener {
            hideKeyBoard()
        }
    }

    private fun initCallback() {
        viewModel.categoryList.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                (sender as ObservableField<List<Category>>).get()?.let {
                    binding.viewPagerLists.offscreenPageLimit =
                        viewModel.categoryList.get()?.size ?: 0
                }
            }
        })

        viewModel.briefRequestList.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                (sender as ObservableField<List<BriefRequest>>).get()?.let {
                    if (listItemAdapter.count != 0) {
                        for (i in 0 until listItemAdapter.count) {
                            listItemAdapter.getFragmentByPosition(i)?.let { fragment ->
                                fragment.loadFragmentData()
                            }
                        }
                    }
                }
            }
        })
    }

    private fun hideKeyBoard() {
        imm.hideSoftInputFromWindow(et_query.windowToken, 0)

    }

}