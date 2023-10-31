package com.example.homework_1_android

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.mainRecycleView)
        val orientation = Resources.getSystem().configuration.orientation
        adapter = MainAdapter(orientation)
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        else{
            recyclerView.layoutManager = GridLayoutManager(this, 3)
        }
        recyclerView.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ARRAY_LIST_KEY, ArrayList(adapter.getItems()))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val itemList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getParcelableArrayList(ARRAY_LIST_KEY, CountViews::class.java)?.toMutableList()
        } else {
            @Suppress("DEPRECATION") savedInstanceState.getParcelableArrayList(ARRAY_LIST_KEY)
        }

        itemList?.let { adapter.setItems(it) }
    }

    companion object{
        const val ARRAY_LIST_KEY = "itemList"
    }

}