package com.seoulcontest.firstcitizen.ui.upload

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ActivityDetailAddressBinding
import com.seoulcontest.firstcitizen.network.NaverPlaceHelper
import com.seoulcontest.firstcitizen.network.vo.NaverPlaceResponse
import com.seoulcontest.firstcitizen.ui.dialog.InputDetailAddressDialog
import com.seoulcontest.firstcitizen.util.DetailAddressInterface
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailAddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAddressBinding
    private val detailAddressAdapter by lazy { DetailAddressAdapter(this) }
    private lateinit var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_address)
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        initRecyclerView()
        initEvent()
    }

    private fun initRecyclerView() {
        binding.rvDetailAddress.adapter = detailAddressAdapter
    }

    private fun initEvent() {
        binding.btSearch.setOnClickListener {
            hideKeyBoard()

            val vm = MainViewModel.getInstance()
            val lonLat = "${vm.currLongitude},${vm.currLatitude}"
            val query = binding.etQuery.text.toString()

            NaverPlaceHelper
                .getInstance()
                .apiService
                .getAddressByQuery(
                    "9usgnvn86f",
                    "aoCLVqkHRzb3Eb5VdGd85sAHghmgfPd2RBFwWsHi",
                    query, lonLat, "popularity"
                )
                .enqueue(object : Callback<NaverPlaceResponse> {
                    override fun onFailure(call: Call<NaverPlaceResponse>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<NaverPlaceResponse>, response: Response<NaverPlaceResponse>) {
                        if (response.isSuccessful) {
                            val data = response.body()

                            if (data != null) {
                                detailAddressAdapter.setData(data.places)
                            } else {
                                Toast.makeText(this@DetailAddressActivity, "검색되는 데이터가 없습니다.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Log.d("test", response.errorBody()?.string())
                            Toast.makeText(this@DetailAddressActivity, "검색하는데 실패했습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        }

        detailAddressAdapter.setOnItemClickListener(object : DetailAddressAdapter.OnItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                val item = detailAddressAdapter.getData(pos)
                val mainAddress = "${item.roadAddress} ${item.name}"

                InputDetailAddressDialog().apply {
                    arguments = Bundle().apply { putString("address", mainAddress) }

                    setOnDialogButtonClickListener(object : DetailAddressInterface {
                        override fun onConfirmSelected(detailAddress: String) {
                            setResult(Activity.RESULT_OK, Intent().apply {
                                putExtra("mainAddress", mainAddress)
                                putExtra("detailAddress", detailAddress)
                                putExtra("latitude", item.y.toDouble())
                                putExtra("longitude", item.x.toDouble())
                            })

                            finish()
                        }

                        override fun onCancelSelected() {
                            Toast.makeText(this@DetailAddressActivity, "취소되었습니다.", Toast.LENGTH_SHORT)
                                .show()
                        }

                    })
                    show(supportFragmentManager.beginTransaction(), "")
                }
            }
        })
    }


    private fun hideKeyBoard() {
        imm.hideSoftInputFromWindow(et_query.windowToken, 0)
    }

}

