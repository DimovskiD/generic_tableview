package com.deluxe1.generictableviewdemo

import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.RowType
import com.deluxe1.generic_tableview.view.CustomBooleanTextView
import com.deluxe1.generic_tableview.view.CustomBooleanView
import com.deluxe1.generic_tableview.view.CustomSwitchView
import com.deluxe1.generic_tableview.view.CustomTextView


data class PackagePlanItem (val title: String, val quantity: Int, val monthlyFee: Float)
    : GenericListElement(
    mapOf(
        CustomTextView("Item", title) to true,
        CustomBooleanTextView("Quantity", true) to true,
        CustomBooleanView("Monthly Fee", value = false, true) to true
    ), type =  RowType.POSITIVE_NEGATIVE
)