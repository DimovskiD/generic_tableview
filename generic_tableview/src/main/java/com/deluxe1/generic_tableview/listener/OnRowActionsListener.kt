package com.deluxe1.generic_tableview.listener

import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.RowAction

/**Provides an interface for handling clicking on actions in a table row*/
interface OnRowActionsListener<K : GenericListElement>  {
    /**Invoked when an action was performed on a table row
     * @param element - the element that corresponds to the clicked row
     * @param action - the action that was performed. One of [RowAction]*/
    fun onAction(element : K, action : RowAction)
}