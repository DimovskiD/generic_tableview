package com.deluxe1.generic_tableview.listener

import com.deluxe1.generic_tableview.GenericListElement

/**Provides an interface for handling clicking on a table row*/
interface OnRowClickListener<K : GenericListElement> {
    /**Invoked when a row from the table was clicked
     * @param row - the element that corresponds to the clicked row*/
    fun onRowClicked(row: K)
}