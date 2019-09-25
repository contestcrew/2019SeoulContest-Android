package com.seoulcontest.firstcitizen.ui.upload

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.databinding.ItemUploadPicBinding
import com.seoulcontest.firstcitizen.util.ItemResizer

class UploadAdapter(val mContext: Context, var uploadImageArray: List<Uri>) :
    RecyclerView.Adapter<UploadAdapter.UploadViewHolder>() {

    lateinit var binding: ItemUploadPicBinding

    override fun onBindViewHolder(holder: UploadViewHolder, position: Int) {
        holder.bind(uploadImageArray[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UploadViewHolder {

        val inflater = LayoutInflater.from(mContext)

        binding = DataBindingUtil.inflate(inflater, R.layout.item_upload_pic, parent, false)

        return UploadViewHolder(binding)
    }

    override fun getItemCount(): Int = uploadImageArray.size

    inner class UploadViewHolder(var binding: ItemUploadPicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uploadImage: Uri) {

            val resizer = ItemResizer(mContext)

            binding.imgUpload.maxWidth = resizer.getDisplayWidth()
            binding.imgUpload.maxHeight = resizer.getDisplayHeight()

            Glide.with(mContext).load(uploadImage)
                .override(resizer.getDisplayWidth(), resizer.getDisplayHeight())
                .into(binding.imgUpload)

        }

    }

}