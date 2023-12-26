package com.example.homework3android.ui.home

sealed class HomeIntent {
    data object LoadData:HomeIntent()
}