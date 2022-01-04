package com.deluxe1.generictableviewdemo

import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.LinearLayout
import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.R
import com.deluxe1.generic_tableview.RowAction
import com.deluxe1.generic_tableview.databinding.GenericViewHolderBinding
import com.deluxe1.generic_tableview.listener.OnRowActionsListener
import com.deluxe1.generic_tableview.viewholder.GenericViewHolder
import com.google.android.material.button.MaterialButton
import kotlin.math.roundToInt

/**Example of a custom view holder - see [MyActionTypeDetector] for example of how to map it*/
class CustomButtonViewHolder<T : GenericListElement>(binding : GenericViewHolderBinding,
                                               maxColumns : Int,
                                               private val onRowActionsListener: OnRowActionsListener<T>?) :
    GenericViewHolder<T>(binding, maxColumns) {

    override fun getView(element: T): View {
        return MaterialButton(
            ContextThemeWrapper(
                binding.container.context,
                R.style.button
            )
        ).apply {
            if (element.actionTextRes != null)
                text = context.getText(element.actionTextRes!!)
            if (element.actionIconRes != null) {
                setIconResource(element.actionIconRes!!)
            }
            val buttonWidth = if (element.actionIconRes != null) LinearLayout.LayoutParams.WRAP_CONTENT
            else TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60f, context.resources.displayMetrics)
                .roundToInt()
            val params = LinearLayout.LayoutParams(buttonWidth, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams = params
            setOnClickListener { onRowActionsListener?.onAction(element, RowAction.POSITIVE) }
            setBackgroundColor(context.getColor(com.deluxe1.generictableviewdemo.R.color.teal_700))
        }
    }
}
