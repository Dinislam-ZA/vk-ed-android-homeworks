package com.example.homework_2_android.data.api

import com.example.homework_2_android.data.model.Gif
import com.example.homework_2_android.data.model.GifResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GifApiService {

    @GET("v1/gifs/trending")
    suspend fun fetchGifs(@Query("api_key") apiKey: String,
                          @Query("limit") limit: Int,
                          @Query("offset") offset: Int):Response<GifResponse>


}