package com.example.homework3android.data.repositories

import com.example.homework3android.data.dto.HomeDTO
import com.example.homework3android.data.model.UserModel
import com.example.homework3android.localstorage.DataBase

class HomeRepository {

    private val db = DataBase()

    fun getData():HomeDTO{
        val posts = db.getAllPosts()
        val users = db.getAllUsers()
        return HomeDTO(users, posts)
    }

}