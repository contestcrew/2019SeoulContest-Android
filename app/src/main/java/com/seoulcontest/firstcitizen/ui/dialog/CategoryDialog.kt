package com.seoulcontest.firstcitizen.ui.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.DialogCategoryBinding
import com.seoulcontest.firstcitizen.ui.upload.UploadActivity
import kotlin.String

class CategoryDialog(private val mContext: Context) : DialogFragment() {

    lateinit var category: DialogCategoryBinding
    private val stringArray: Array<String> = arrayOf("긴급 똥 휴지", "분실", "접촉 사고", "뺑소니")


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 2019.09.16 다이얼로그 바깥쪽 클릭 무시 by Hudson
        dialog.setCanceledOnTouchOutside(false)

        // 2019.09.15 adapter 세팅 by Hudson
        category.lvCategory.adapter =
            CategoryAdapter(mContext, stringArray)
        category.lvCategory.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, postion, id ->

                // 2019.09.16 String 선택 후 UploadActivity로 이동 by Hudosn
                val uploadIntent = Intent(mContext,UploadActivity::class.java)

                uploadIntent.putExtra("category",stringArray.get(postion))
                startActivity(uploadIntent)
                dismiss()
            }

        // 2019.09.15 취소 버튼 클릭 시 CategoryDialog 종료 by Hudson
        category.btnCancelrequest.setOnClickListener {
            dismiss()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 2019.09.15 카테고리 다이얼로그 관련 로직 추가 by Hudson
        category = DataBindingUtil.inflate(inflater, R.layout.dialog_category, container, false)
        return category.root
    }

}
