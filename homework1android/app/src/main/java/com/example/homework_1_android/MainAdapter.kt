package com.example.homework_1_android

import android.content.res.Configuration

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.parcelize.Parcelize
import java.lang.IllegalArgumentException

class MainAdapter(private val orientation: Int, val listener: MainAdapterOnClickListener) : RecyclerView.Adapter<ViewHolder>() {

    private var items = mutableListOf<CountViews>(CountViews.ButtonView)
    private lateinit var recyclerView: RecyclerView

    inner class NumberViewHolder(itemView: View) : ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.itemTextView)

        //color for background, not text
        fun onBind(item: CountViews.NumberView, position: Int){
            textView.text = item.number
            if ((position % 2) == 0) itemView.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.blue)) else itemView.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.red))
            itemView.setOnClickListener {
                listener.onNumberClickListener(position)
            }
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
        if(this::recyclerView.isInitialized){
            recyclerView.smoothScrollToPosition(items.size-2)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = getSpanSizeLookup(orientation)
    }

    private fun getSpanSizeLookup(orientation: Int): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (items[position]) {
                    is CountViews.NumberView -> 1
                    is CountViews.ButtonView -> {
                        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
                            4
                        }
                        else{
                            3
                        }
                    }
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

interface MainAdapterOnClickListener{

    fun onNumberClickListener(pos: Int)

}