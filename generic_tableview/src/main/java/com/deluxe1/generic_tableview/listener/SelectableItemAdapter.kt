package com.deluxe1.generic_tableview.listener

interface SelectableItemAdapter<T> {
    fun clearSelection() : Boolean
    fun getSelectedItems() : List<T>
}