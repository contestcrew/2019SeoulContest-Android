package com.seoulcontest.firstcitizen.ui.main.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.data.vo.Category
import com.seoulcontest.firstcitizen.databinding.FragmentListBinding
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
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
        binding.tabLayoutCatergory.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                hideKeyBoard()
            }
        })

        binding.viewPagerLists.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                hideKeyBoard()
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        })

        binding.btSearch.setOnClickListener {
            hideKeyBoard()
            (listItemAdapter.getFragmentByPosition(0) as ListItemFragment).searchRequest(binding.etQuery.text.toString())

            val tab = binding.tabLayoutCatergory.getTabAt(0)!!
            tab.select()
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