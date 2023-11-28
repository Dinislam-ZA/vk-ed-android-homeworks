package com.example.homework_2_android.ui.adapters

import androidx.recyclerview.widget.DiffUtil

class ItemViewDiffItemCallback(
    private val oldList: List<ItemView>,
    private val newList: List<ItemView>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return if (oldItem is ItemView.GifView && newItem is ItemView.GifView) {
            oldItem.gif.id == newItem.gif.id
        } else {
            oldItem::class == newItem::class
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return if (oldItem is ItemView.GifView && newItem is ItemView.GifView) {
            oldItem.gif.id == newItem.gif.id
        } else {
            oldItem::class == newItem::class
        }
    }
}