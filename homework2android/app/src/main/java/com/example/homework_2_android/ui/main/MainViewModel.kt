package com.example.homework_2_android.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.homework_2_android.data.model.Gif
import com.example.homework_2_android.data.repository.GifRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: GifRepository) : ViewModel() {

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    private val gifs: MutableList<Gif> = mutableListOf()

    init {
        processIntent(MainIntent.LoadGifs)
    }

   fun processIntent(intent: MainIntent) {
        when(intent){
            is MainIntent.LoadGifs -> loadGifs()
            is MainIntent.ResetGifs -> resetPagination()
        }
    }

    private fun loadGifs(){
        _state.value = MainState.Loading
        viewModelScope.launch {
            try {
                gifs.addAll(repository.getTrendingGifs())
                Log.d("gifs in viewmodel", gifs.toString())
                _state.value = MainState.Success(gifs)
            }
            catch (e:Exception){
                Log.d("error in viewmodel", "Something goes wrong")
               _state.value = MainState.Error("Something goes wrong...")
            }
        }
    }

    fun resetPagination() {
        repository.resetPagination()
        loadGifs()
    }

    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return MainViewModel(
                    GifRepository()
                ) as T

            }
        }
    }
}