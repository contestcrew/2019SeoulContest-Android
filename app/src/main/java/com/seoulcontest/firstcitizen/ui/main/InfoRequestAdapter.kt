package com.seoulcontest.firstcitizen.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ItemRequestBinding

class InfoRequestAdapter(
    private val context: Context,
    private val requestList: ArrayList<Category>
) :
    RecyclerView.Adapter<InfoRequestAdapter.RequestHolder>() {

    private lateinit var binding: ItemRequestBinding

    override fun onBindViewHolder(holder: RequestHolder, position: Int) {

           // 카테고리별로 바인딩
            holder.bind(requestList[position], context)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        binding = ItemRequestBinding.inflate(inflater, parent, false)
        return RequestHolder(binding)
    }

    override fun getItemCount(): Int = requestList.size

    inner class RequestHolder(private val binding: ItemRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category?, context: Context) {

            // 각 카테고리별 이미지 적용
            if (category?.type != "") {

                val resourceId = context.resources.getIdentifier(
                    category!!.type,
                    "drawable",
                    context.packageName
                )
                Glide.with(context).load(resourceId).into(binding.ivRequest)

            }
        }
    }
}