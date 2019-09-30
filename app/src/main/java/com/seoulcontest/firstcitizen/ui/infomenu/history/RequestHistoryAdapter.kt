package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.Request
import com.seoulcontest.firstcitizen.databinding.ItemHistoryRequestBinding

class RequestHistoryAdapter(private var requestArr: Array<Request>) :
    RecyclerView.Adapter<RequestHistoryAdapter.RequestHistoryViewHolder>() {

    lateinit var binding: ItemHistoryRequestBinding

    override fun onBindViewHolder(holder: RequestHistoryViewHolder, position: Int) {
        holder.bind(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestHistoryViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_history_request, parent, false
        )
        return RequestHistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = requestArr.size


    inner class RequestHistoryViewHolder(binding: ItemHistoryRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

            with(binding) {

                tvDate.text = requestArr[position].occurredAt
                tvContent.text = requestArr[position].content
                tvPoint.text = requestArr[position].score.toString()
                tvBonus.text = requestArr[position].categoryScore.toString()
                tvStatus.text = requestArr[position].status

                cvItemRequestHistory.setOnClickListener {


                }


            }

        }
    }
}