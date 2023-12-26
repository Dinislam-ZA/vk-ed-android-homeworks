package com.example.homework3android.ui.adapters

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework3android.R
import com.example.homework3android.data.model.PostModel
import com.example.homework3android.data.model.UserModel
import com.example.homework3android.databinding.ItemAccountBinding
import com.example.homework3android.databinding.ItemArtPostBinding
import com.example.homework3android.databinding.ItemFilterBinding

class HomeMainAdapter(
    private val artists: List<UserModel>,
    private val filters: List<String>,
    private val posts: List<PostModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> R.layout.item_account
        1 -> R.layout.item_filter
        else -> R.layout.item_art_post
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            R.layout.item_account -> ItemAccountBinding.inflate(inflater, parent, false)
            R.layout.item_filter -> ItemFilterBinding.inflate(inflater, parent, false)
            else -> ItemArtPostBinding.inflate(inflater, parent, false)
        }
        return when (viewType) {
            R.layout.item_account -> ArtistsViewHolder(binding as ItemAccountBinding)
            R.layout.item_filter -> FiltersViewHolder(binding as ItemFilterBinding)
            else -> PostViewHolder(binding as ItemArtPostBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArtistsViewHolder -> holder.bind(artists)
            is FiltersViewHolder -> holder.bind(filters)
            is PostViewHolder -> holder.bind(posts[position - 2]) // Adjust the index for posts
        }
    }

    override fun getItemCount(): Int = 2 + posts.size

    class ArtistsViewHolder(private val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(artists: List<UserModel>) {
            // TODO: Set up artist RecyclerView with ArtistAdapter
        }
    }

    class FiltersViewHolder(private val binding: ItemFilterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filters: List<String>) {
            // TODO: Set up filters RecyclerView with FilterAdapter
        }
    }

    class PostViewHolder(private val binding: ItemArtPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostModel) {
            // TODO: Bind post data to UI elements
        }
    }
}


sealed class HomeItem {
    object Artists : HomeItem()
    object Filters : HomeItem()
    data class Post(val post: PostModel) : HomeItem()
}
