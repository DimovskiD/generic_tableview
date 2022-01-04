package com.deluxe1.generic_tableview.listener

interface SelectableItemAdapter<out T> {
    fun clearSelection() : Boolean
    fun getSelectedItems() : List<T>
}