package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.data.vo.Report
import com.seoulcontest.firstcitizen.databinding.ItemPooCategoryDetailBinding

class PooCategoryAdapter : RecyclerView.Adapter<PooCategoryAdapter.RequestDetailViewHolder>() {
    private val data = mutableListOf<Report>()

    fun setData(list: List<Report>?) {
        if (list != null) {
            data.clear()
            data.addAll(list)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestDetailViewHolder {
        val binding = ItemPooCategoryDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RequestDetailViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RequestDetailViewHolder, position: Int) {
        return holder.bind(data[position])
    }

    inner class RequestDetailViewHolder(val binding: ItemPooCategoryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Report) {
            binding.report = item

            //event 처리
        }
    }

}