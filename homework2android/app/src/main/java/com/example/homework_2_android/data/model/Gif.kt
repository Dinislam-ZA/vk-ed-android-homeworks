package com.example.homework_2_android.data.model

data class Gif(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)

data class GifResponse(
    val data: List<GifData>
)

data class GifData(
    val id: String,
    val images: GifImages
)

data class GifImages(
    val original: GifImageDetail
)

data class GifImageDetail(
    val url: String,
    val width: String,
    val height: String
)
