package com.example.homework_2_android.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.cachedIn
import com.example.homework_2_android.data.model.Gif
import com.example.homework_2_android.data.repository.GifRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(val repository: GifRepository) : ViewModel() {

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    init {
        processIntent(MainIntent.LoadGifs)
    }

   fun processIntent(intent: MainIntent) {
        when(intent){
            is MainIntent.LoadGifs -> loadGifs()
        }
    }

    private fun loadGifs(){
        _state.value = MainState.Loading
        viewModelScope.launch {
            try {
                val gifs = repository.getTrendingGifs().cachedIn(viewModelScope)
                Log.d("gifs", gifs.toString())
                gifs.collectLatest {
                    _state.value = MainState.Success(it)
                }
            }
            catch (e:Exception){
               _state.value = MainState.Error("Something goes wrong...")
            }
        }
    }


    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras


                return MainViewModel(
                    GifRepository()
                ) as T

            }
        }
    }
}