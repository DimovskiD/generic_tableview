package com.deluxe1.generic_tableview.view

import android.content.Context
import android.graphics.Typeface
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StringRes
import kotlin.math.roundToInt

/**A text view that can be added in the [com.deluxe1.generic_tableview.GenericListElement]
 * @property titleResId - title of the column under which this view is inserted
 * @property value - text that the view should present
 * @property weight - the weight for the width of the column - the bigger the weight, the more space the column occupies*/
class CustomTextView private constructor(private val value: String, private val weight: Float) : GenericView() {


    constructor(title: String, value: String, weight: Float = 1f) : this(value, weight) {
        this.title = title
    }

    constructor(@StringRes titleResId: Int, value: String, weight: Float = 1f) : this(value, weight) {
        this.titleResId = titleResId
    }

    override fun getView(context: Context, isHeader: Boolean): View = TextView(context).apply {
        text = if (isHeader) getTitle(context) else value;
        ellipsize = TextUtils.TruncateAt.END; typeface =
        if (isHeader) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        maxLines = 2
    }

    override fun getLayoutParams(context: Context): LinearLayout.LayoutParams {
        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weight)
        params.gravity = Gravity.CENTER
        val margin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 5f, context.resources.displayMetrics
        ).roundToInt()
        params.setMargins(margin, margin, margin, margin)
        return params
    }


}