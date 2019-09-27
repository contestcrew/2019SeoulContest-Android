package com.seoulcontest.firstcitizen.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ItemCategoryBinding
import kotlin.String

class CategoryAdapter(mContext : Context, private val arrayList : Array<String>)  : BaseAdapter() {

    private val data : Array<String> = arrayList
    private val inflater : LayoutInflater = LayoutInflater.from(mContext)
    lateinit var item : ItemCategoryBinding


    override fun getView(position : Int, convertView: View?, parent : ViewGroup?): View {

        return if (convertView == null){

            item = DataBindingUtil.inflate(inflater,R.layout.item_category,parent,false)
            item.btnCategory.text = arrayList[position]

            item.root

        } else {

            convertView
        }
    }

    override fun getItem(position: Int): Any ? = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = data.size
}