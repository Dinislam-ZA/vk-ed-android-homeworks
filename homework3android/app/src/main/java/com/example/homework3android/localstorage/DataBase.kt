package com.example.homework3android.localstorage

import com.example.homework3android.data.model.PostModel
import com.example.homework3android.data.model.UserModel

class DataBase {

    private val randomPostModels = listOf(
        PostModel(title = "azpmanvuaj", description = "itfstqpxpxvswbpvtpth", image = "https://api.adorable.io/avatars/926", likes = 23, chats = 22, author = 4L),
        PostModel(title = "bobsxvzgbb", description = "tampmyikkkpwmtanmjyz", image = "https://api.adorable.io/avatars/184", likes = 80, chats = 29, author = 2L),
        PostModel(title = "edyyulifyh", description = "dhzhdkfgjipygcgemudi", image = "https://api.adorable.io/avatars/561", likes = 18, chats = 17, author = 2L),
        PostModel(title = "nbduheiwhf", description = "ztaieeiaqdkaxkyhtplt", image = "https://api.adorable.io/avatars/367", likes = 81, chats = 27, author = 1L),
        PostModel(title = "wngxksuvny", description = "wlxpasorqgqmoleulzjs", image = "https://api.adorable.io/avatars/741", likes = 94, chats = 0, author = 3L)
    )


    private val randomUserModels = listOf(
        UserModel(id = 0L, username = "aposavtn", avatar = "https://api.adorable.io/avatars/516"),
        UserModel(id = 1L, username = "urxcaytz", avatar = "https://api.adorable.io/avatars/221"),
        UserModel(id = 2L, username = "ovwsebkv", avatar = "https://api.adorable.io/avatars/427"),
        UserModel(id = 3L, username = "zmdartgz", avatar = "https://api.adorable.io/avatars/750"),
        UserModel(id = 4L, username = "vhizrjci", avatar = "https://api.adorable.io/avatars/604")
    )


    fun getAllPosts():List<PostModel>{
        return randomPostModels
    }

    fun getAllUsers():List<UserModel>{
        return randomUserModels
    }


}