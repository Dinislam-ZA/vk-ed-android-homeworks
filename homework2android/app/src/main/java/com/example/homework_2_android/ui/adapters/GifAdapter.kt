package com.example.homework_2_android.ui.adapters

import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.homework_2_android.R
import com.example.homework_2_android.data.model.Gif
import com.example.homework_2_android.databinding.GifItemBinding
import com.example.homework_2_android.databinding.LoadingItemBinding
import com.example.homework_2_android.databinding.RepeatButtonItemBinding
import java.lang.Exception

class GifAdapter(val repeatButtonClickListener: ReloadButtonClickListener) : RecyclerView.Adapter<ViewHolder>() {

    private var items: MutableList<ItemView> = mutableListOf()

    fun showLoadingBar(){
        if (items.isNotEmpty()){
            if ((items.last() is ItemView.ButtonView) or (items.last() is ItemView.LoadingView)) removeLoadingOrErrorState()
        }
        items.add(ItemView.LoadingView)
        notifyItemInserted(items.size - 1)
    }

    fun showReloadButton(){
        if (items.isNotEmpty()){
            if ((items.last() is ItemView.ButtonView) or (items.last() is ItemView.LoadingView)) removeLoadingOrErrorState()
        }
        items.add(ItemView.ButtonView)
        notifyItemInserted(items.size - 1)
    }

    fun removeLoadingOrErrorState(){
        if (items.isNotEmpty() && (items.last() is ItemView.ButtonView || items.last() is ItemView.LoadingView)) {
            items.removeAt(items.size - 1)
        }
        notifyItemRemoved(items.size)
    }

    fun setListItem(newList: List<Gif>) {
        val newItems = newList.map { ItemView.GifView(it) }
        val startPosition = items.size
        items = newItems.toMutableList()
        notifyItemRangeInserted(startPosition, newItems.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType){
            VIEW_TYPE_GIF -> {
                val binding = GifItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GifViewHolder(binding)
            }
            VIEW_TYPE_LOADING -> {
                val binding = LoadingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LoadingViewHolder(binding)
            }
            VIEW_TYPE_RELOAD_BUTTON -> {
                val binding = RepeatButtonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ErrorViewHolder(binding)
            }

            else -> {
                throw Exception("Wrong view type")
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        if (holder is GifViewHolder){
            val gifItem = (item as ItemView.GifView).gif
            Glide.with(holder.binding.imageView.context)
                .load(gifItem.url)
                .placeholder(R.drawable.loading_gif_placeholder)
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
    }


    override fun getItemCount(): Int = items.size

    inner class GifViewHolder(val binding: GifItemBinding): RecyclerView.ViewHolder(binding.root)

    inner class LoadingViewHolder(val binding: LoadingItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            val layoutParams = itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams
            layoutParams?.isFullSpan = true
        }
    }

    inner class ErrorViewHolder(val binding: RepeatButtonItemBinding): RecyclerView.ViewHolder(binding.root) {

        init {

            val layoutParams = itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams
            layoutParams?.isFullSpan = true

            binding.gifReloadButton.setOnClickListener {
                repeatButtonClickListener.onClickReload()
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is ItemView.GifView -> VIEW_TYPE_GIF
            is ItemView.LoadingView -> VIEW_TYPE_LOADING
            is ItemView.ButtonView -> VIEW_TYPE_RELOAD_BUTTON
        }
    }

    companion object{
        const val VIEW_TYPE_GIF = 1
        const val VIEW_TYPE_LOADING = 2
        const val VIEW_TYPE_RELOAD_BUTTON = 3
    }
}

class GifDiffItemCallback(
    private val oldList: List<Gif>,
    private val newList: List<Gif>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

sealed class ItemView{

    data class GifView(val gif:Gif):ItemView()

    data object ButtonView : ItemView()

    data object LoadingView : ItemView()
}

interface ReloadButtonClickListener{
    fun onClickReload()
}