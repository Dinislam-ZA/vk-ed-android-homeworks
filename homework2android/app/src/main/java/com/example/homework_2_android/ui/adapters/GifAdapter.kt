package com.example.homework_2_android.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_2_android.R
import com.example.homework_2_android.data.model.Gif
import com.example.homework_2_android.databinding.GifItemBinding

class GifAdapter : PagingDataAdapter<Gif, GifAdapter.GifViewHolder>(GifDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val binding = GifItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GifViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifItem = getItem(position)!!
        Glide.with(holder.binding.imageView.context)
            .load(gifItem.url)
            .placeholder(R.drawable.loading_gif_placeholder)
            .error(R.drawable.error_drawable)
            .into(holder.binding.imageView)

        // Установка пропорций изображения
        holder.binding.imageView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                holder.binding.imageView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val imageViewWidth = holder.binding.imageView.width
                if (imageViewWidth > 0 && gifItem.width > 0 && gifItem.height > 0) {
                    val params = holder.binding.imageView.layoutParams
                    params.height = (imageViewWidth * (gifItem.height.toFloat() / gifItem.width.toFloat())).toInt()
                    holder.binding.imageView.layoutParams = params
                }
            }
        })
    }


    inner class GifViewHolder(val binding: GifItemBinding): RecyclerView.ViewHolder(binding.root)
}

private object GifDiffItemCallback : DiffUtil.ItemCallback<Gif>() {

    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem == newItem
    }
}