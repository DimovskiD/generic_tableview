package com.deluxe1.generictableviewdemo

import com.deluxe1.generic_tableview.ActionType
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.listener.OnRowActionsListener
import com.deluxe1.generic_tableview.viewholder.GenericViewHolder

object MyActionType : ActionType() {
    override fun <T : GenericListElement> getViewHolder(
        binding: GenericViewHolderBinding,
        onRowActionsListener: OnRowActionsListener<T>?,
        maxColumns: Int
    ): GenericViewHolder<T>  =
        CheckViewHolder(binding, maxColumns)
}