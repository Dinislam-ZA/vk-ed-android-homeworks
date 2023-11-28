package com.example.homework_2_android.ui.main

import androidx.constraintlayout.motion.utils.ViewState
import com.example.homework_2_android.data.model.Gif

sealed class MainState {
    object Loading: MainState()
    object Error: MainState()
    data class Success(val gifs: List<Gif>?): MainState()
}
