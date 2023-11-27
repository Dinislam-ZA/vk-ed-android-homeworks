package com.example.homework_2_android.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.homework_2_android.databinding.LoadStateViewItemBinding

class GifLoadStateAdapter(
    private val retryCallback: () -> Unit
) : LoadStateAdapter<GifLoadStateAdapter.GifLoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): GifLoadStateViewHolder {
        val binding = LoadStateViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GifLoadStateViewHolder(binding, retryCallback)
    }

    override fun onBindViewHolder(holder: GifLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class GifLoadStateViewHolder(
        private val binding: LoadStateViewItemBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            // Установка ViewHolder на полную ширину в StaggeredGridLayoutManager
            val layoutParams = itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams
            layoutParams?.isFullSpan = true

            binding.retryButton.setOnClickListener { retryCallback() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMessage.text = loadState.error.localizedMessage
            }
            binding.pagingProgressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMessage.isVisible = loadState is LoadState.Error
        }
    }

}