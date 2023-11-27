package com.example.homework_2_android.data.repository

import com.example.homework_2_android.Constants
import com.example.homework_2_android.data.api.RetrofitClient
import com.example.homework_2_android.data.mappers.GifMapper
import com.example.homework_2_android.data.model.Gif

class GifRepository {
    private val api = RetrofitClient.service

    suspend fun getTrendingGifs(): List<Gif>? {
        val response = api.fetchGifs(Constants.apiKey, 10, 1)
        return response.body()?.let { GifMapper.mapGifResponseListToGifList(it) }
    }
}