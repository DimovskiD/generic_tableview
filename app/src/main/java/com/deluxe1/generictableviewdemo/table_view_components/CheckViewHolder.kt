package com.deluxe1.generictableviewdemo.table_view_components

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.viewholder.GenericViewHolder
import com.deluxe1.generictableviewdemo.R

/**A view holder that presents a row in the table view with [ImageView] action as a last column */
class CheckViewHolder<T: GenericListElement>(binding : GenericViewHolderBinding, maxColumns : Int) :
    GenericViewHolder<T>(binding, maxColumns) {
    override fun getView(element : T): View = ImageView(binding.container.context).apply {
        setImageResource(R.drawable.ic_baseline_check_24)
        imageTintList = ColorStateList.valueOf(context.getColor(R.color.teal_500))
    }
}