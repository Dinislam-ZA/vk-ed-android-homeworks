package com.example.homework3android.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostVO(
    val title:String,
    val description:String,
    val image: String,
    var likes: Int,
    var chats: Int,
    val author:String?,
    val avatar:String?
): Parcelable
