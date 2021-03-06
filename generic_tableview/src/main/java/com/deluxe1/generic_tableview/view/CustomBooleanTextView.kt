package com.deluxe1.generic_tableview.view

import android.content.Context
import android.graphics.Typeface
import android.text.Layout
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StringRes
import com.deluxe1.generic_tableview.R
import kotlin.math.roundToInt

/**A simple YES/NO boolean view that can be added in the [com.deluxe1.generic_tableview.GenericListElement]
 * @property titleResId - resource id of the title string resource for the column under which this view is inserted
 * @property value - true if the checkbox needs to be checked, false otherwise */
class CustomBooleanTextView private constructor(private val value : Boolean): GenericView() {

    constructor(title: String, value: Boolean) : this(value) {
        this.title = title
    }

    constructor(@StringRes titleResId: Int, value: Boolean) : this(value) {
        this.titleResId = titleResId
    }

    override fun getView(context: Context, isHeader: Boolean): View =
        TextView(context).apply {
            if (!isHeader) {
                isAllCaps = true
                if (value) setTextColor(context.getColor(R.color.av_deep_orange))
                text = context.getString( if (value) R.string.yes else R.string.no)
            } else {
                text = getTitle(context)
                typeface = Typeface.DEFAULT_BOLD
            }

        }

    override fun getLayoutParams(context: Context): LinearLayout.LayoutParams {
        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f)
        params.gravity = Gravity.CENTER
        val margin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 5f, context.resources.displayMetrics).roundToInt()
        params.setMargins(margin, margin, margin, margin)
        return params
    }


}
