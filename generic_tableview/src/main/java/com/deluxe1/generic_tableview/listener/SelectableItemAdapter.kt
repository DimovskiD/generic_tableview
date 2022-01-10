package com.deluxe1.generic_tableview.listener

/**Adapter for selectable items*/
interface SelectableItemAdapter<out T> {
    /**Call to clear the selected items
     * @return true if any items were selected before clearing the selection */
    fun clearSelection() : Boolean
    /**@return the list of currently selected items*/
    fun getSelectedItems() : List<T>
}