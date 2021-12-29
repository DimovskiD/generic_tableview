package com.deluxe1.generic_tableview.viewholder

import android.view.View
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.listener.OnRowClickListener
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding


/**A view holder that presents a row in the table view without an action column */
class NoActionViewHolder<T:GenericListElement>(binding : GenericViewHolderBinding, maxColumns : Int) :
    GenericViewHolder<T>(binding, maxColumns) {
    override fun getView(element: T): View? = null
}