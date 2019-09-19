package com.seoulcontest.firstcitizen.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.databinding.ItemInfoMenuBinding
import com.seoulcontest.firstcitizen.ui.service.NoticeActivity
import com.seoulcontest.firstcitizen.ui.service.ServiceTermsActivity

class InfoMenuAdapter(
    private val mContext: Context,
    private val mInfoMenuArray: Array<String>,
    private var totalCount: Int
) : RecyclerView.Adapter<InfoMenuAdapter.InfoMenuViewHolder>() {

    override fun onBindViewHolder(viewHolder: InfoMenuViewHolder, position: Int) {

        // 뷰 홀더 타입별로 서로 다른 뷰 바인딩
        if (position == 0) {
            viewHolder.bind(mInfoMenuArray[position], totalCount)
        } else {
            viewHolder.bind(mInfoMenuArray[position], -1)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoMenuViewHolder {

        val inflater: LayoutInflater = LayoutInflater.from(mContext)

        // 2019.09.19 ViewHolder 리턴 by Hudson
        return InfoMenuViewHolder(
            ItemInfoMenuBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = 5

    inner class InfoMenuViewHolder(private val binding: ItemInfoMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(menu: String, totalCount: Int) {

            var intent: Intent? = null

            binding.tvMenu.text = menu

            // 첫번째 뷰에만 활성화된 의뢰 수 표시
            if (totalCount > -1) {
                binding.layoutCount.visibility = View.VISIBLE
                binding.tvActiveCount.text = totalCount.toString()

                // 그 외의 경우 뷰 INVISIBLE 처리
            } else {
                binding.layoutCount.visibility = View.INVISIBLE
            }


            // 2019.09.19 각 메뉴 클릭 시 해당 액티비티로 이동 by Hudson
            itemView.setOnClickListener {

                when (menu) {

                    "공지사항" -> {
                        intent = Intent(mContext, NoticeActivity::class.java)

                    }

                    "이용약관" -> {
                        intent = Intent(mContext, ServiceTermsActivity::class.java)

                    }

                    "내정보" -> {
                        intent = Intent(mContext, MyInfoActivity::class.java)
                    }

                    "제보" -> {
                        intent = Intent(mContext, InfoListActivity::class.java)
                    }

                    "의뢰" -> {

                        intent = Intent(mContext, InfoListActivity::class.java)
                    }
                }

                mContext.startActivity(intent)
            }
        }
    }
}



