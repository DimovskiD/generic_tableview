package com.deluxe1.generictableviewdemo.model

import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.row_type.ButtonRowType
import com.deluxe1.generic_tableview.row_type.ChevronRowType
import com.deluxe1.generic_tableview.row_type.GenericRowType
import com.deluxe1.generic_tableview.view.CustomBooleanView
import com.deluxe1.generic_tableview.view.CustomImageTextView
import com.deluxe1.generic_tableview.view.CustomTextView
import com.deluxe1.generictableviewdemo.R

/**Sample class for displaying client info*/
data class Client (val id: Int, val avatar: String, val company: String, val country: String, val phone: String)
    : GenericListElement(
    mapOf(
        CustomTextView(R.string.id, id.toString()) to false,
        CustomImageTextView(R.string.company, company, 2f, avatar, false) to true,
        CustomTextView(R.string.country, country, 1f) to true,
        CustomTextView(R.string.phone, value = phone) to true
    ), type =  GenericRowType
)