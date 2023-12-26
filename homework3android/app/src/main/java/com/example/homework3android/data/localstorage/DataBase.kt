package com.example.homework3android.data.localstorage

import com.example.homework3android.data.model.PostModel
import com.example.homework3android.data.model.UserModel

class DataBase {

    private val randomPostModels = listOf(
        PostModel(title = "Golden Gate at Dusk", description = "Feel the calm and serenity of untouched beaches.", image = "https://source.unsplash.com/random/", likes = 576, chats = 224, author = 1L),
        PostModel(title = "Desert Safari", description = "Discover the beauty of urban landscapes through our exploration.", image = "https://source.unsplash.com/random/", likes = 665, chats = 342, author = 5L),
        PostModel(title = "Rainforest Journey", description = "Journey into the dense rainforest and discover the untold mysteries within.", image = "https://source.unsplash.com/random/", likes = 772, chats = 297, author = 3L),
        PostModel(title = "Autumn Leaves", description = "The fall brings a burst of colors with leaves turning into shades of orange and red.", image = "https://source.unsplash.com/random/", likes = 481, chats = 399, author = 4L),
        PostModel(title = "City Lights", description = "The city comes alive at night with lights that twinkle like stars.", image = "https://source.unsplash.com/random/", likes = 433, chats = 485, author = 2L),
        PostModel(title = "Starry Night", description = "The night sky has never been more vivid, with stars shining brightly.", image = "https://source.unsplash.com/random/", likes = 720, chats = 284, author = 4L),
        PostModel(title = "Wildlife Adventure", description = "Join us on an adventure into the wild, and experience nature in its purest form.", image = "https://source.unsplash.com/random/", likes = 193, chats = 80, author = 4L),
        PostModel(title = "Serenity Beach", description = "The mountains are calling, and the snow is glistening under the sunlight.", image = "https://source.unsplash.com/random/", likes = 368, chats = 236, author = 2L)
    )



    private val randomUserModels = listOf(
        UserModel(id = 0L, username = "user_0", avatar = "https://source.unsplash.com/random/"),
        UserModel(id = 1L, username = "user_1", avatar = "https://source.unsplash.com/random/"),
        UserModel(id = 2L, username = "user_2", avatar = "https://source.unsplash.com/random/"),
        UserModel(id = 3L, username = "user_3", avatar = "https://source.unsplash.com/random/"),
        UserModel(id = 4L, username = "user_4", avatar = "https://source.unsplash.com/random/"),
        UserModel(id = 5L, username = "user_5", avatar = "https://source.unsplash.com/random/"),
        UserModel(id = 6L, username = "user_6", avatar = "https://source.unsplash.com/random/"),
        UserModel(id = 7L, username = "user_7", avatar = "https://source.unsplash.com/random/")
    )



    fun getAllPosts():List<PostModel>{
        return randomPostModels
    }

    fun getAllUsers():List<UserModel>{
        return randomUserModels
    }


}