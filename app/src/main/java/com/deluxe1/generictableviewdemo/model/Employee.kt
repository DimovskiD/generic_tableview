package com.deluxe1.generictableviewdemo.model

import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.view.CustomBooleanView
import com.deluxe1.generic_tableview.view.CustomTextView
import com.deluxe1.generictableviewdemo.R
import com.deluxe1.generictableviewdemo.table_view_components.CustomButtonActionType

/**Sample class for displaying employee info*/
data class Employee (val id: Int, val firstName: String, val lastName: String, val fullTime: Boolean)
    : GenericListElement(
    mapOf(
        CustomTextView(R.string.id, id.toString()) to false,
        CustomTextView(R.string.first_name, firstName, 1f) to true,
        CustomTextView(R.string.last_name, lastName, 1.2f) to true,
        CustomBooleanView(R.string.full_time, value = fullTime, true) to true
    ), type =  CustomButtonActionType, R.string.remove
)