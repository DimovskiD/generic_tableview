package com.deluxe1.generic_tableview.listener

interface OnBindHeaderListener<T> {
    /**Called when the first element of the adapter has been instantiated
     * Triggers creating the header
     * @param item - a sample item from the adapter - used to calculate the positioning of the header titles
     * @param viewType - type of the views in the adapter - used to position the headers vertically
     * @param maxColumns - maximum number of columns (header will have maxNumber - 1 - for the action element)*/
    fun onHeaderBound(item : T, viewType : Int, maxColumns : Int)
}