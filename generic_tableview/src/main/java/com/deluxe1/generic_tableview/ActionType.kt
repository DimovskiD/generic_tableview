package com.deluxe1.generic_tableview

import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.listener.OnRowActionsListener
import com.deluxe1.generic_tableview.viewholder.GenericViewHolder

/**Represents action of a table row*/
abstract class ActionType {

    /**The integer representation of the action - defaults to the hash code
     * Override only if you absolutely must*/
    fun getIntValue() = hashCode()

    /**Instantiates a view for the action type
     * @param binding - the generic ViewHolder binding
     * @param onRowActionsListener - listens for on click events on the row action - can be null
     * @param maxColumns - the maximum number of columns per row
     * @return a view holder with the correct action (if any)*/
    abstract fun <T : GenericListElement> getViewHolder(
        binding: GenericViewHolderBinding,
        onRowActionsListener: OnRowActionsListener<T>?,
        maxColumns: Int
    ) : GenericViewHolder<T>
}