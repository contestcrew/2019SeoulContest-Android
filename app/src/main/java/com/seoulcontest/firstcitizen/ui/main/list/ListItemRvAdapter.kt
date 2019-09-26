package com.seoulcontest.firstcitizen.ui.main.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.data.vo.BriefRequest
import com.seoulcontest.firstcitizen.databinding.ItemListCategoryBinding
import com.seoulcontest.firstcitizen.ui.detail.DetailActivity
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel

class ListItemRvAdapter : RecyclerView.Adapter<ListItemRvAdapter.ListItemViewHolder>() {
    private val data = mutableListOf<BriefRequest>()
    private lateinit var binding: ItemListCategoryBinding

    fun loadData(category: Int?) {
        data.clear()

        if (category != null) {
            MainViewModel.getInstance().loadBriefRequestsByCategory(category)
                ?.let { data.addAll(it) }
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        binding = DataBindingUtil.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_category,
                parent,
                false
            )
        )!!

        return ListItemViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ListItemViewHolder(binding: ItemListCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BriefRequest) {
            with(binding) {
                binding.currRequest = item

                //카테고리별로 이미지뷰 세팅
                when (item.category) {
                    1 -> ivCategory.setImageResource(R.drawable.ic_restroom)
                    2 -> ivCategory.setImageResource(R.drawable.ic_loss)
                    3 -> ivCategory.setImageResource(R.drawable.ic_crash)
                    4 -> ivCategory.setImageResource(R.drawable.ic_missing)
                }

                //클릭이벤트 처리
                root.setOnClickListener {
                    val intent = Intent().apply {
                        setClass(it.context, DetailActivity::class.java)
                        putExtra("id", item.id)
                        putExtra("lat", item.latitude)
                        putExtra("lng", item.longitude)
                        putExtra("category", item.category)
                    }

                    it.context.startActivity(intent)
                }

                executePendingBindings()
            }
        }
    }
}