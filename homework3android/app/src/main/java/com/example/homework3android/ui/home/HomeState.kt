package com.example.homework3android.ui.home

sealed class HomeState {
    data object Load:HomeState()
    data class Success(val data:List<String>):HomeState()

}