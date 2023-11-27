package com.example.homework_2_android.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework_2_android.Constants
import com.example.homework_2_android.data.api.RetrofitClient
import com.example.homework_2_android.data.mappers.GifMapper
import com.example.homework_2_android.data.model.Gif
import retrofit2.HttpException

class GifPagingSource:  PagingSource<Int, Gif>() {

    private val api = RetrofitClient.service

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        try {
            val pageNumber = params.key ?: 1
            val pageSize = params.loadSize.coerceAtMost(25)
            val offset = pageSize * pageNumber
            val response = api.fetchGifs(Constants.apiKey , pageSize, offset)
            return if (response.isSuccessful) {
                val gifs = response.body()?.let { GifMapper.mapGifResponseListToGifList(it) }
                val nextPageNumber = if (gifs?.isEmpty() == true) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
                LoadResult.Page(gifs!!, prevPageNumber, nextPageNumber)
            } else {
                Log.d("err", "error 1")
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            Log.d("err", "error 2")
            return LoadResult.Error(e)
        } catch (e: Exception) {
            Log.d("err", e.toString())
            return LoadResult.Error(e)
        }
    }
}