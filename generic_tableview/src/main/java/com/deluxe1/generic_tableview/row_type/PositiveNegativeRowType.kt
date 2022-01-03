package com.deluxe1.generic_tableview.row_type

import com.deluxe1.generic_tableview.ActionType
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.listener.OnRowActionsListener
import com.deluxe1.generic_tableview.viewholder.DoubleButtonViewHolder
import com.deluxe1.generic_tableview.viewholder.GenericViewHolder

/**Implementation of [ActionType] for a row with two action buttons (positive and negative)*/
object PositiveNegativeRowType : ActionType() {
    override fun <T : GenericListElement> getViewHolder(
        binding: GenericViewHolderBinding,
        onRowActionsListener: OnRowActionsListener<T>?,
        maxColumns: Int
    ): GenericViewHolder<T>  = DoubleButtonViewHolder(binding, maxColumns, onRowActionsListener)

}