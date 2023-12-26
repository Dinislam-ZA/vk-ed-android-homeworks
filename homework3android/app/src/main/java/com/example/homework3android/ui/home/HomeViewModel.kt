package com.example.homework3android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.homework3android.data.repositories.HomeRepository

class HomeViewModel(val repository: HomeRepository) : ViewModel() {
    // TODO: Implement the ViewModel



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