package com.example.homework_2_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework_2_android.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

// Внедрить изменения по рассчету высоты и ширины imageView
// Размеры плейсхолдеров и ошибок поправить
// Покрасить плейсхолдер и ошибку
// Починить стейты экрана