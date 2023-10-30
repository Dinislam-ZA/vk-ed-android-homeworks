package com.example.homework_1_android

import android.graphics.Color
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.parcelize.Parcelize
import java.lang.IllegalArgumentException

class MainAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var items = mutableListOf<CountViews>(CountViews.ButtonView)

    inner class NumberViewHolder(itemView: View) : ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.itemTextView)

        fun onBind(item: CountViews.NumberView, position: Int){
            textView.text = item.number
            if ((position % 2) == 0) textView.setTextColor(Color.parseColor("#0000FF")) else textView.setTextColor(Color.parseColor("#FF0000"))
        }
    }

    inner class ButtonViewHolder(itemView: View) : ViewHolder(itemView) {
        private val button: Button = itemView.findViewById(R.id.addButton)

        init {
            button.setOnClickListener {
                addItem()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            1 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
                NumberViewHolder(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.button_item, parent, false)
                ButtonViewHolder(view)
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        when (holder){
            is NumberViewHolder -> holder.onBind(item as CountViews.NumberView, position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is CountViews.NumberView -> VIEW_TYPE_NUMBER
            is CountViews.ButtonView -> VIEW_TYPE_BUTTON
        }
    }

    fun getItems() = items

    fun setItems(newItems: MutableList<CountViews>){
        items = newItems
        notifyDataSetChanged()
    }

    fun addItem(){
        val newItem = "${items.size}"
        items.add(items.size - 1, CountViews.NumberView(newItem))
        notifyItemInserted(items.size - 2)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = getSpanSizeLookup()
    }

    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (items[position]) {
                    is CountViews.NumberView -> 1
                    is CountViews.ButtonView -> 3
                }
            }
        }
    }

    companion object{
        const val VIEW_TYPE_NUMBER = 1
        const val VIEW_TYPE_BUTTON = 2
    }
}


sealed class CountViews: Parcelable{
    @Parcelize
    data class NumberView(val number:String):CountViews()
    @Parcelize
    data object ButtonView : CountViews()
}