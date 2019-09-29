package com.seoulcontest.firstcitizen.ui.upload

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seoulcontest.firstcitizen.databinding.ItemUploadBinding
import com.seoulcontest.firstcitizen.util.ItemResizer


class ImageUploadAdapter(val context: Context) :
    RecyclerView.Adapter<ImageUploadAdapter.UploadViewHolder>() {
    private val uploadImageArray = mutableListOf<Uri>()

    fun setUriImages(data: List<Uri>) {
        uploadImageArray.clear()
        uploadImageArray.addAll(data)
        notifyDataSetChanged()
    }

    fun setStringImages(data: List<String>?) {
        uploadImageArray.clear()

        if (data.isNullOrEmpty()) {
            val uri = Uri.parse("android.resource://${context.packageName}/drawable/no_image")
            uploadImageArray.add(uri)
        } else {
            uploadImageArray.addAll(data.map { Uri.parse(it) })
            Log.d("test", "data size : ${data.size}")
        }

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UploadViewHolder, position: Int) {
        holder.bind(uploadImageArray[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UploadViewHolder {
        val binding = ItemUploadBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UploadViewHolder(binding)
    }

    override fun getItemCount(): Int = uploadImageArray.size

    inner class UploadViewHolder(var binding: ItemUploadBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(uploadImage: Uri) {
            with(ItemResizer(binding.root.context)) {

<<<<<<< HEAD:app/src/main/java/com/seoulcontest/firstcitizen/ui/upload/UploadAdapter.kt
            with(ItemResizer(binding.root.context)) {

                binding.imgUpload.maxWidth = getDisplayWidth()
                binding.imgUpload.maxHeight = getDisplayHeight()

                Glide.with(mContext).load(uploadImage).override(getDisplayWidth(), getDisplayHeight())
=======
                Glide
                    .with(binding.root.context)
                    .load(uploadImage)
>>>>>>> master:app/src/main/java/com/seoulcontest/firstcitizen/ui/upload/ImageUploadAdapter.kt
                    .into(binding.imgUpload)
            }
        }
    }
}