package com.example.homework3android.data.repositories

import com.example.homework3android.data.dto.HomeDTO
import com.example.homework3android.data.model.UserModel

class HomeRepository {


    fun getData():HomeDTO{
        return HomeDTO(listOf(UserModel(id = 0L, username = "aposavtn", avatar = "https://api.adorable.io/avatars/516")), listOf())
    }

}