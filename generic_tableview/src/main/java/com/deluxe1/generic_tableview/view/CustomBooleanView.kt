package com.deluxe1.generic_tableview.view

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import com.deluxe1.generic_tableview.R
import kotlin.math.roundToInt

/**A checkbox view that can be added in the [com.deluxe1.generic_tableview.GenericListElement]
 * @property titleResId -  resource id of the title string resource for the column under which this view is inserted
 * @property value - true if the checkbox needs to be checked, false otherwise */
class CustomBooleanView private constructor(private val value : Boolean, private val isEnabled : Boolean): GenericView() {

    constructor(title: String, value: Boolean, isEnabled: Boolean = false) : this(value, isEnabled) {
        this.title = title
    }

    constructor(@StringRes titleResId: Int, value: Boolean, isEnabled: Boolean = false) : this(value, isEnabled) {
        this.titleResId = titleResId
    }

    override fun getView(context: Context, isHeader: Boolean): View =
        if (isHeader) CustomTextView(getTitle(context), "").getView(context, isHeader)
        else LayoutInflater.from(context).inflate(R.layout.custom_checkbox_no_text, null)

    override fun getLayoutParams(context: Context): LinearLayout.LayoutParams {
        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f)
        params.gravity = Gravity.CENTER
        val margin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 5f, context.resources.displayMetrics).roundToInt()
        params.setMargins(margin, margin, margin, margin)
        return params
    }


}
