package com.example.homework3android.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework3android.data.model.UserModel
import com.example.homework3android.databinding.ItemAccountBinding
import com.example.homework3android.databinding.ItemFilterBinding

class ArtistAdapter(private val artists: List<UserModel>) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(artists[position])
    }

    override fun getItemCount(): Int = artists.size

    class ArtistViewHolder(private val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: UserModel) {
            with(binding){
                textUserName.text = artist.username
                Glide.with(root).load(artist.avatar).into(imageUserAvatar)
            }
        }
    }
}


