package com.example.homework_1_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val color = intent.extras?.getInt("Color")
        getWindow().getDecorView().setBackgroundColor(color!!);
    }
}