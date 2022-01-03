package com.deluxe1.generic_tableview.row_type

import com.deluxe1.generic_tableview.ActionType
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.listener.OnRowActionsListener
import com.deluxe1.generic_tableview.viewholder.GenericViewHolder
import com.deluxe1.generic_tableview.viewholder.NoActionViewHolder

/**Default Implementation of [ActionType] for a row with no action*/
object GenericRowType : ActionType() {

    override fun <T : GenericListElement> getViewHolder(
        binding: GenericViewHolderBinding,
        onRowActionsListener: OnRowActionsListener<T>?,
        maxColumns: Int
    ): GenericViewHolder<T> = NoActionViewHolder(binding, maxColumns)

}