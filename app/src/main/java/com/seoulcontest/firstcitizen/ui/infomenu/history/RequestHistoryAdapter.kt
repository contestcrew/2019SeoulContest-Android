package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.databinding.ItemHistoryRequestBinding

class RequestHistoryAdapter :
    RecyclerView.Adapter<RequestHistoryAdapter.RequestHistoryViewHolder>() {

    private val data = mutableListOf<BriefRequest>()

    fun setData(list: List<BriefRequest>?) {
        data.clear()

        if (list != null) {
            data.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestHistoryViewHolder {
        val binding = ItemHistoryRequestBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return RequestHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestHistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class RequestHistoryViewHolder(val binding: ItemHistoryRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BriefRequest) {
            binding.briefRequest = item

            //카테고리별로 이미지뷰 세팅
            when (item.category) {
                1 -> binding.ivCategory.setImageResource(R.drawable.ic_restroom)
                2 -> binding.ivCategory.setImageResource(R.drawable.ic_loss)
                3 -> binding.ivCategory.setImageResource(R.drawable.ic_crash)
                4 -> binding.ivCategory.setImageResource(R.drawable.ic_missing)
            }

            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, RequestDetailActivity::class.java).apply {
                    putExtra("id", item.id)
                    putExtra("category", item.category)
                    putExtra("lat", item.latitude)
                    putExtra("lng", item.longitude)
                })
            }
        }
    }
}
