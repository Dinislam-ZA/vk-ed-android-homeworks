package com.example.homework_2_android.data.repository

import com.example.homework_2_android.Constants
import com.example.homework_2_android.data.api.RetrofitClient
import com.example.homework_2_android.data.mappers.GifMapper
import com.example.homework_2_android.data.model.Gif

class GifRepository {
    private val api = RetrofitClient.service

    private var currentPage = 0
    private val pageSize = 20

    suspend fun getTrendingGifs(): List<Gif> {
        val offset = currentPage * pageSize
        val response = api.fetchGifs(Constants.apiKey, pageSize, currentPage)
        return if (response.isSuccessful) {
            currentPage++
            response.body()?.let { GifMapper.mapGifResponseListToGifList(it) } ?: emptyList()
        } else {
            throw Exception("Error fetching gifs: ${response.errorBody()?.string()}")
        }
    }

    fun resetPagination() {
        currentPage = 0
    }
}