package com.seoulcontest.firstcitizen.ui.infomenu.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.data.vo.Request
import com.seoulcontest.firstcitizen.databinding.ItemHelpDetailBinding

class HelpDetailAdapter(private var requestPicList : ArrayList<Request>) : RecyclerView.Adapter<HelpDetailAdapter.HelpDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpDetailViewHolder {

        var binding = ItemHelpDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HelpDetailViewHolder(binding)
    }

    override fun getItemCount(): Int =  requestPicList.size

    override fun onBindViewHolder(holder: HelpDetailViewHolder, position: Int) {

        holder.bind(requestPicList[position])
    }


    inner class HelpDetailViewHolder(val binding: ItemHelpDetailBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : Request) {


        }
    }
}