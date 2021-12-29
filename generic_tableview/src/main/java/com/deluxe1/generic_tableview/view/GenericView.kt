package com.deluxe1.generic_tableview.view

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.deluxe1.generic_tableview.R

/**Defines what every view that can be included in the [com.deluxe1.generic_tableview.GenericListElement]'s columns*/
abstract class GenericView {

    protected var titleResId : Int = R.string.empty_string
    protected var title : String? = null

    protected fun getTitle(context: Context): String = title?: context.getString(titleResId)


    /**@returns the view for the given element*/
    abstract fun getView(context: Context, isHeader: Boolean) : View
    /**@return layout parameters for the view*/
    abstract fun getLayoutParams(context: Context) : LinearLayout.LayoutParams
}
