package com.example.homework_2_android.ui.main

import androidx.constraintlayout.motion.utils.ViewState
import com.example.homework_2_android.data.model.Gif

sealed class MainState {

    object Loading: MainState()
    data class Error(val message: String): MainState()

    data class Gifs(val gifs: List<Gif>): MainState()

}
