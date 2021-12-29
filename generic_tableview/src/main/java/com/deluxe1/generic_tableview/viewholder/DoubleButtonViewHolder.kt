package com.deluxe1.generic_tableview.viewholder

import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.R
import com.deluxe1.generic_tableview.RowAction
import com.deluxe1.generic_tableview.listener.OnRowActionsListener
import com.deluxe1.generic_tableview.listener.OnRowClickListener
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding


/**A view holder that presents a row in the table view with two action buttons (positive and negative) as a last column */
class DoubleButtonViewHolder<T : GenericListElement>(binding : GenericViewHolderBinding,
                                                     maxColumns : Int,
                                                     val onRowActionsListener: OnRowActionsListener<T>?)
    : GenericViewHolder<T>(binding, maxColumns) {

    override fun getView(element : T): View {
        val positiveButton = ImageButton(binding.container.context).apply {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams = params
            setImageDrawable(ContextCompat.getDrawable(binding.container.context, R.drawable.ic_positive))
            setBackgroundColor(Color.TRANSPARENT)
            setOnClickListener { onRowActionsListener?.onAction(element, RowAction.POSITIVE)}
        }
        val negativeButton = ImageButton(binding.container.context).apply {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams = params
            setImageDrawable(ContextCompat.getDrawable(binding.container.context, R.drawable.ic_negative))
            setBackgroundColor(Color.TRANSPARENT)
            setOnClickListener { onRowActionsListener?.onAction(element, RowAction.NEGATIVE)}
        }
        return LinearLayout(binding.container.context).apply {
            orientation = LinearLayout.HORIZONTAL
            addView(negativeButton)
            addView(positiveButton)
        }
    }
}