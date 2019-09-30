package com.seoulcontest.firstcitizen.ui.upload

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.databinding.ItemDetailAddressBinding
import com.seoulcontest.firstcitizen.network.vo.NaverPlaceResponse

class DetailAddressAdapter(val context: Context) :
    RecyclerView.Adapter<DetailAddressAdapter.DetailAddressViewHolder>() {
    private val data = mutableListOf<NaverPlaceResponse.Place>()

    fun setData(places: List<NaverPlaceResponse.Place>) {
        data.clear()
        data.addAll(places)
        notifyDataSetChanged()
    }

    fun getData(pos: Int): NaverPlaceResponse.Place {
        return data[pos]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAddressViewHolder {
        val binding = ItemDetailAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DetailAddressViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DetailAddressViewHolder, position: Int) {
        holder.bind(data[position])
    }

    public interface OnItemClickListener {
        fun onItemClick(v: View, pos: Int)
    }

    private lateinit var listener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class DetailAddressViewHolder(val binding: ItemDetailAddressBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NaverPlaceResponse.Place) {
            val mainAddress = item.name
            binding.tvDetailAddress.text = mainAddress

            itemView.setOnClickListener {
                if (listener != null) {
                    listener.onItemClick(it, adapterPosition)
                }
            }
        }
    }

}