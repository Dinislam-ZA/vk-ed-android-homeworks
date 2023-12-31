package com.example.homework3android.ui.adapters

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework3android.R
import com.example.homework3android.data.model.PostModel
import com.example.homework3android.data.model.PostVO
import com.example.homework3android.data.model.UserModel
import com.example.homework3android.databinding.ItemAccountListBinding
import com.example.homework3android.databinding.ItemArtPostBinding
import com.example.homework3android.databinding.ItemFilterListBinding

class HomeMainAdapter(val listener: HomeMainListener, val filters:List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var artists: List<UserModel> = listOf()
    private var posts: List<PostModel> = listOf()

    fun setData(newArtists: List<UserModel>, newPosts:List<PostModel>){
        artists = newArtists
        posts = newPosts
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> R.layout.item_account_list
        1 -> R.layout.item_filter_list
        else -> R.layout.item_art_post
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            R.layout.item_account_list -> ItemAccountListBinding.inflate(inflater, parent, false)
            R.layout.item_filter_list -> ItemFilterListBinding.inflate(inflater, parent, false)
            else -> ItemArtPostBinding.inflate(inflater, parent, false)
        }
        return when (viewType) {
            R.layout.item_account_list -> ArtistsViewHolder(binding as ItemAccountListBinding)
            R.layout.item_filter_list -> FiltersViewHolder(binding as ItemFilterListBinding)
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

    inner class ArtistsViewHolder(private val binding: ItemAccountListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(artists: List<UserModel>) {
            binding.accountsRV.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ArtistAdapter(artists)
            }
        }
    }

    inner class FiltersViewHolder(private val binding: ItemFilterListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(filters: List<String>) {
            binding.filterRV.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = FilterAdapter(filters)
            }
        }
    }

    inner class PostViewHolder(private val binding: ItemArtPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostModel) {
            with(binding){
                val author = artists.find { a -> a.id == post.author }
                username.text = author?.username
                artworkTitle.text = post.title
                artworkDescription.text= post.description
                likeCount.text = post.likes.toString()
                commentCount.text = post.chats.toString()
                Glide.with(root).load(post.image).into(artworkImage)
                Glide.with(root).load(author?.avatar).centerCrop().into(profileImage)
                moreButton.setOnClickListener {
                    listener.onMoreButtonClick(PostVO(post.title, post.description, post.image, post.likes, post.chats, author?.username, author?.avatar))
                }
            }
        }
    }
}


interface HomeMainListener{

    fun onMoreButtonClick(post: PostVO)

}


