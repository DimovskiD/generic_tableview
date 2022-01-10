package com.deluxe1.generic_tableview.listener

interface OnItemSelectedListener<T> {
    /**Called when an item of the adapter has been selected
     * @param item - the selected/deselected item
     * @param isSelected - if true the item is selected, false if it is deselected
     * @param totalSelected - the total number of selected items*/
    fun onItemSelected(item : T, isSelected : Boolean, totalSelected : Int)
}