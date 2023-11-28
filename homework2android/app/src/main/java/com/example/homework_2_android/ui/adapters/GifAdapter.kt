package com.example.homework_2_android.ui.adapters

import android.util.DisplayMetrics
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

        val context = holder.binding.imageView.context
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        val widthDp = displayMetrics.widthPixels
        val imageViewWidthDp = ((widthDp / 2f) - 4f * 2f).toInt()
        with(holder.binding.imageView.layoutParams) {
            this.width = ViewGroup.LayoutParams.MATCH_PARENT
            this.height =
                (imageViewWidthDp * (gifItem.height.toFloat() / gifItem.width.toFloat())).toInt()
            holder.binding.imageView.layoutParams = this
        }
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