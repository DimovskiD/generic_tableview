package com.deluxe1.generic_tableview.sample

import com.deluxe1.generic_tableview.GenericListElement
import com.deluxe1.generic_tableview.view.CustomBooleanView
import com.deluxe1.generic_tableview.view.CustomTextView

/**Sample list element to prove that the adapter works with different implementations*/
class PhoneElement(
    phoneNumber: String,
    smsSupport: Boolean,
    setupPrice: Int,
    pricePerMonth: Int
) : GenericListElement(
    mapOf(
        CustomTextView("Phone number", phoneNumber, 1.5f) to true,
        CustomBooleanView("SMS support", smsSupport) to true,
        CustomTextView("Setup Price", setupPrice.toString(), 0.7f) to true,
        CustomTextView("Price per month", pricePerMonth.toString(), 0.7f) to true
    )
)