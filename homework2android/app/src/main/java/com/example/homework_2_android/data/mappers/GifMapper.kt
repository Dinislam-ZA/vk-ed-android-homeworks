package com.example.homework_2_android.data.mappers

import com.example.homework_2_android.data.model.Gif
import com.example.homework_2_android.data.model.GifData
import com.example.homework_2_android.data.model.GifResponse

object GifMapper {
    fun mapGifResponseListToGifList(gifResponse: GifResponse):List<Gif>{
        return gifResponse.data.map { gif ->
            Gif(
                id = gif.id,
                url = gif.images.original.url,
                width = gif.images.original.width.toInt(),
                height = gif.images.original.height.toInt()
            )
        }
    }
}