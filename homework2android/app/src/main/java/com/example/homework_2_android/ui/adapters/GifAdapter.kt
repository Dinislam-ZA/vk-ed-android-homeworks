package com.example.homework_2_android.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_2_android.R
import com.example.homework_2_android.data.model.Gif
import com.example.homework_2_android.databinding.GifItemBinding

class GifAdapter : RecyclerView.Adapter<GifAdapter.GifViewHolder>() {

    private var gifs: List<Gif> = listOf()

    fun setListItem(newList: List<Gif>){
        gifs = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val binding = GifItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GifViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifItem = gifs[position]
        Glide.with(holder.binding.imageView.context)
            .load(gifItem.url)
            .placeholder(R.drawable.loading_gif_placeholder)
            .into(holder.binding.imageView)

        // Установка пропорций изображения
        val params = holder.binding.imageView.layoutParams
        val imageViewWidth = holder.binding.imageView.width
        params.height = (imageViewWidth * (gifItem.height.toFloat() / gifItem.width.toFloat())).toInt()
        holder.binding.imageView.layoutParams = params
    }

    override fun getItemCount(): Int = gifs.size

    inner class GifViewHolder(val binding: GifItemBinding): RecyclerView.ViewHolder(binding.root)
}