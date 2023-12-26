package com.example.homework3android.data.model

import android.media.Image

data class PostModel(
    val title:String,
    val description:String,
    val image: String,
    var likes: Int,
    var chats: Int,
    val author:Long
)
