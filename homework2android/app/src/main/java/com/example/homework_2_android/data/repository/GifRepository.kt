package com.example.homework_2_android.data.repository

import android.util.Log
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
        val response = api.fetchGifs(Constants.apiKey, pageSize, offset)
        return if (response.isSuccessful) {
            currentPage++
            Log.d("offset", offset.toString())
            Log.d("current page", currentPage.toString())
            response.body()?.let { GifMapper.mapGifResponseListToGifList(it) } ?: emptyList()
        } else {
            throw Exception("Error fetching gifs: ${response.errorBody()?.string()}")
        }
    }

    fun resetPagination() {
        currentPage = 0
    }
}