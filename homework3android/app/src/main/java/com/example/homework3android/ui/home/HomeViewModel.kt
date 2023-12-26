package com.example.homework3android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.homework3android.data.repositories.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState> = _state

    private var isLoading = false

    init {
        processIntent(HomeIntent.LoadData)
    }

    fun processIntent(intent: HomeIntent) {
        when(intent){
            is HomeIntent.LoadData -> loadData()
        }
    }

    private fun loadData(){
        if(isLoading) return
        isLoading = true
        _state.value = HomeState.Load
        viewModelScope.launch {
            delay(2000)
            _state.value = HomeState.Success(repository.getData())
            isLoading = false
        }
    }



    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                return HomeViewModel(
                    HomeRepository()
                ) as T

            }
        }
    }
}