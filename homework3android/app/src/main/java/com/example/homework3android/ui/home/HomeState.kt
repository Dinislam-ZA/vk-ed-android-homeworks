package com.example.homework3android.ui.home

import com.example.homework3android.data.dto.HomeDTO

sealed class HomeState {
    data object Load:HomeState()
    data class Success(val data:HomeDTO):HomeState()

}