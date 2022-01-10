package com.deluxe1.generictableviewdemo.table_view_components

import com.deluxe1.generic_tableview.ActionType
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.listener.OnRowActionsListener
import com.deluxe1.generic_tableview.viewholder.GenericViewHolder

object CustomButtonActionType : ActionType() {
    /**Instantiates a view for the action type
     * @param binding - the generic ViewHolder binding
     * @param onRowActionsListener - listens for on click events on the row action - can be null
     * @param maxColumns - the maximum number of columns per row
     * @return a view holder with the correct action (if any)*/
    override fun <T : GenericListElement> getViewHolder(
        binding: GenericViewHolderBinding,
        onRowActionsListener: OnRowActionsListener<T>?,
        maxColumns: Int
    ): GenericViewHolder<T> = CustomButtonViewHolder(binding, maxColumns, onRowActionsListener)
}