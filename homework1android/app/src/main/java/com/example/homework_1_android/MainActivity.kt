package com.example.homework_1_android

import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.mainRecycleView)
        adapter = MainAdapter()
        recyclerView.adapter = adapter

        if(Resources.getSystem().configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        else{
            recyclerView.layoutManager = GridLayoutManager(this, 3)
        }

        val button = findViewById<View>(R.id.mainButton)
        button.setOnClickListener {
            adapter.addItem()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("itemList", ArrayList(adapter.getItems()))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val itemList = savedInstanceState.getStringArrayList("itemList")?.toMutableList()
        itemList?.let { adapter.setItems(it) }
    }

}