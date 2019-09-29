package com.seoulcontest.firstcitizen.ui.upload

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ItemUploadBinding
import com.seoulcontest.firstcitizen.util.ItemResizer

class UploadAdapter(val mContext: Context, var uploadImageArray: List<Uri>) :
    RecyclerView.Adapter<UploadAdapter.UploadViewHolder>() {

    lateinit var binding: ItemUploadBinding

    override fun onBindViewHolder(holder: UploadViewHolder, position: Int) {
        holder.bind(uploadImageArray[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UploadViewHolder {
        val inflater = LayoutInflater.from(mContext)
        binding = DataBindingUtil.inflate(inflater, R.layout.item_upload, parent, false)
        return UploadViewHolder(binding)
    }

    override fun getItemCount(): Int = uploadImageArray.size

    inner class UploadViewHolder(var binding: ItemUploadBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(uploadImage: Uri) {

            with(ItemResizer(mContext)) {

                binding.imgUpload.maxWidth = getDisplayWidth()
                binding.imgUpload.maxHeight = getDisplayHeight()

                Glide.with(mContext).load(uploadImage).override(getDisplayWidth(), getDisplayHeight())
                    .into(binding.imgUpload)
            }
        }
    }
}