package com.example.homework_2_android.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.homework_2_android.Constants
import com.example.homework_2_android.data.api.RetrofitClient
import com.example.homework_2_android.data.mappers.GifMapper
import com.example.homework_2_android.data.model.Gif
import com.example.homework_2_android.data.pagingsource.GifPagingSource
import kotlinx.coroutines.flow.Flow

class GifRepository {
    private val api = RetrofitClient.service

    suspend fun getTrendingGifs(): Flow<PagingData<Gif>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 40
            ),
            pagingSourceFactory = { GifPagingSource() }
        ).flow
    }
}