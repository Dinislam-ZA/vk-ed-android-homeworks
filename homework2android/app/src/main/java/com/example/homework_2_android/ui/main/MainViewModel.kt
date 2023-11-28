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

    private var isLoading = false

    init {
        processIntent(MainIntent.LoadGifs)
    }

   fun processIntent(intent: MainIntent) {
        when(intent){
            is MainIntent.LoadGifs -> loadGifs()
        }
    }

    private fun loadGifs(){
        if(isLoading) return
        isLoading = true
        _state.value = MainState.Loading
        viewModelScope.launch {
            try {
                _state.value = MainState.Success(repository.getTrendingGifs())
            }
            catch (e:Exception){
               _state.value = MainState.Error
            }
            finally {
                isLoading = false
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                return MainViewModel(
                    GifRepository()
                ) as T

            }
        }
    }
}