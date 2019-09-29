package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.ReportList
import com.seoulcontest.firstcitizen.databinding.ItemHistoryHelpBinding

class HelpHistoryAdapter(val context: Context, var helpHistoryArr: Array<ReportList>) :
    RecyclerView.Adapter<HelpHistoryAdapter.HelpHistoryViewHolder>() {

    override fun onBindViewHolder(holder: HelpHistoryViewHolder, position: Int) {
        // 역순 출력
        holder.bind(helpHistoryArr.size - position)
    }

    lateinit var binding: ItemHistoryHelpBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpHistoryViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_history_help, parent, false)
        return HelpHistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = helpHistoryArr.size

    inner class HelpHistoryViewHolder(var binding: ItemHistoryHelpBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

            with(binding){



            }
        }
    }

}