package com.example.homework3android.data.dto

import com.example.homework3android.data.model.PostModel
import com.example.homework3android.data.model.UserModel

data class HomeDTO(
    val users:List<UserModel>,
    val posts:List<PostModel>
)