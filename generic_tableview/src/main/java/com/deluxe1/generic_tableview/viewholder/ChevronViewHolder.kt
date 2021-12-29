package com.deluxe1.generic_tableview.viewholder

import android.view.View
import android.widget.ImageView
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.listener.OnRowClickListener
import com.deluxe1.generic_tableview.R
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding


/**A view holder that presents a row in the table view with [ImageView] action as a last column */
class ChevronViewHolder<T:GenericListElement>(binding : GenericViewHolderBinding, maxColumns : Int) :
    GenericViewHolder<T>(binding, maxColumns) {
    override fun getView(element : T): View = ImageView(binding.container.context).apply {
        setImageResource(R.drawable.ic_baseline_chevron_right_24)
    }
}