package com.seoulcontest.firstcitizen.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.DialogCategoryBinding

class CategoryDialog(private val mContext: Context) : DialogFragment() {


    lateinit var category: DialogCategoryBinding
    private val categoryArray: Array<String> = arrayOf("긴급 똥 휴지", "분실", "접촉 사고", "뺑소니")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 2019.09.15 카테고리 다이얼로그 관련 로직 추가 by Hudson
        category = DataBindingUtil.inflate(inflater, R.layout.dialog_category, container, false)

        dialog.setCanceledOnTouchOutside(false)

        // 2019.09.15 adapter 세팅 by Hudson
        category.lvCategory.adapter = CategoryAdapter(mContext, categoryArray)
        category.lvCategory.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, postion, id ->

                Toast.makeText(
                    mContext,
                    "${categoryArray.get(postion)} 을/를 클릭하셨습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        // 2019.09.15 취소 버튼 클릭 시 CategoryDialog 종료 by Hudson
        category.btnCancelrequest.setOnClickListener {
            dismiss()
        }


        return category.root
    }

}
