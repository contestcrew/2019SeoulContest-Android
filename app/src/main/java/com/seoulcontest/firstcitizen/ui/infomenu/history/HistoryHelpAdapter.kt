package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.GetReportHistory
import com.seoulcontest.firstcitizen.databinding.ItemHistoryHelpBinding

class HistoryHelpAdapter(var helpHistoryArrGet: Array<GetReportHistory>) :
    RecyclerView.Adapter<HistoryHelpAdapter.HelpHistoryViewHolder>() {

    override fun onBindViewHolder(holder: HelpHistoryViewHolder, position: Int) {
        // 역순 출력
        holder.bind(helpHistoryArrGet.size - position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpHistoryViewHolder {

        var binding: ItemHistoryHelpBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_history_help, parent, false)
        return HelpHistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = helpHistoryArrGet.size

    inner class HelpHistoryViewHolder(var binding: ItemHistoryHelpBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

            with(binding) {

                when (helpHistoryArrGet[position]) {



                }
            }
        }
    }
}