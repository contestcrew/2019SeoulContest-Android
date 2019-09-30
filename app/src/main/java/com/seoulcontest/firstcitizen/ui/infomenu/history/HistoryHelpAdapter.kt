package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.Report
import com.seoulcontest.firstcitizen.databinding.ItemHistoryHelpBinding

class HistoryHelpAdapter(var helpHistoryArrGet: List<Report>) :
    RecyclerView.Adapter<HistoryHelpAdapter.HelpHistoryViewHolder>() {

    override fun onBindViewHolder(holder: HelpHistoryViewHolder, position: Int) {
        holder.bind(helpHistoryArrGet[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpHistoryViewHolder {

        var binding: ItemHistoryHelpBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_history_help, parent, false)
        return HelpHistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = helpHistoryArrGet.size

    inner class HelpHistoryViewHolder(var binding: ItemHistoryHelpBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Report) {

            with(binding) {

               // tvHistoryStatus.text =


            }
        }
    }
}