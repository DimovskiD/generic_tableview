package com.deluxe1.generic_tableview.view

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.appcompat.widget.SwitchCompat
import com.deluxe1.generic_tableview.R
import kotlin.math.roundToInt

/**A switch that can be added in the [com.deluxe1.generic_tableview.GenericListElement]
 * @property titleResId - resource id of the title string resource for the column under which this view is inserted
 * @property switchStatus - the status for the switch element, if true the element should be switched on, else it should be switched off*/
class CustomSwitchView(private var switchStatus : Boolean, private val isEnabled : Boolean = true) : GenericView() {

    constructor(title: String, value: Boolean) : this(value) {
        this.title = title
    }

    constructor(@StringRes titleResId: Int, value: Boolean) : this(value) {
        this.titleResId = titleResId
    }

    override fun getView(context: Context, isHeader: Boolean): View =
        if (isHeader) CustomTextView(getTitle(context), "").getView(context, true)
        else LayoutInflater.from(context).inflate(R.layout.custom_switch_no_text, null).apply {
            findViewById<SwitchCompat>(R.id.switchView).apply {
                isEnabled = this@CustomSwitchView.isEnabled
                isChecked = switchStatus
            }
        }

    override fun getLayoutParams(context: Context): LinearLayout.LayoutParams {
        val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.7f)
        params.gravity = Gravity.CENTER
        val margin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 5f, context.resources.displayMetrics).roundToInt()
        params.setMargins(margin, margin, margin, margin)
        return params
    }

    fun toggleSwitch() { switchStatus = !switchStatus}
}
