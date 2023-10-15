package com.example.homework_1_android

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var items = mutableListOf<String>()

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.itemTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        with(holder){
            textView.text = item
            if ((position % 2) == 0) textView.setTextColor(Color.parseColor("#FF0000")) else textView.setTextColor(Color.parseColor("#0000ff"))
        }

    }

    fun getItems() = items

    fun setItems(newItems: MutableList<String>){
        items = newItems
        notifyDataSetChanged()
    }

    fun addItem(){
        val newItem = "${items.size + 1}"
        items.add(newItem)
        notifyItemInserted(items.size - 1)

        Log.d("mes" ,items.toString())
    }
}