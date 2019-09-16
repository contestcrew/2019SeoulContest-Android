package com.seoulcontest.firstcitizen.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ItemCategoryBinding

class CategoryAdapter(val mContext : Context, val arrayList : Array<String>)  : BaseAdapter() {

    private val data : Array<String>
    private val inflater : LayoutInflater
    lateinit var item : ItemCategoryBinding

    init {
        this.data = arrayList
        this.inflater = LayoutInflater.from(mContext)
    }


    override fun getView(position : Int, convertView: View?, parent : ViewGroup?): View {

        if (convertView == null){

            item = DataBindingUtil.inflate(inflater,R.layout.item_category,parent,false)
            item.btnCategory.text = arrayList[position]

            return item.root

        } else {

            return convertView
        }
    }

    override fun getItem(position: Int): Any ? = arrayList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = arrayList.size
}