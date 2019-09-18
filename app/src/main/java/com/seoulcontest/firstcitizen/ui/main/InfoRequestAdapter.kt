package com.seoulcontest.firstcitizen.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ItemAddRequestBinding
import com.seoulcontest.firstcitizen.databinding.ItemRequestBinding

class InfoRequestAdapter(
    private val context: Context,
    private val requestList: ArrayList<Category>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        const val RC_CATEGORY_TYPE = 0
        const val RC_ADD_TYPE = 1

    }


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {


        // 뷰 홀더 타입별로 서로 다른 뷰 바인딩
        if (viewHolder is RequestCategoryViewHolder) {

            viewHolder.bind(requestList[position], context)

        } else if (viewHolder is AddRequestViewHolder) {

            viewHolder.bind(context)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater: LayoutInflater = LayoutInflater.from(context)

        // 2019.09.19 viewType에 따라 각기 다른 ViewHolder 리턴 by Hudson
        return when (viewType) {

            RC_CATEGORY_TYPE -> RequestCategoryViewHolder(
                ItemRequestBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            RC_ADD_TYPE -> AddRequestViewHolder(
                ItemAddRequestBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> throw IllegalStateException("illegal View Type")
        }
    }

    override fun getItemCount(): Int = requestList.size

    inner class RequestCategoryViewHolder(private val binding: ItemRequestBinding) :
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

    inner class AddRequestViewHolder(private val binding: ItemAddRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context) {

            Glide.with(context).load(R.mipmap.ic_launcher).into(binding.btnAddRequest)
        }
    }

    override fun getItemViewType(position: Int): Int {

        // RequestCategoryViewHolder 리턴
        return when (position) {

            0 -> RC_ADD_TYPE
            else -> RC_CATEGORY_TYPE
        }
    }

}