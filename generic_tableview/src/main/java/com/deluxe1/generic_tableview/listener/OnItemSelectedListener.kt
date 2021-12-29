package com.deluxe1.generic_tableview.listener

interface OnItemSelectedListener<T> {
    fun onItemSelected(item : T, isSelected : Boolean, totalSelected : Int)
}